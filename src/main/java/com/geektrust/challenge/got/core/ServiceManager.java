package com.geektrust.challenge.got.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.UnaryOperator;

import com.geektrust.challenge.got.annotation.JustForTheChallenge;
import com.geektrust.challenge.got.model.Kingdom;
import com.geektrust.challenge.got.util.Pair;

/**
 * A singleton instance which holds all the other services. It is strongly
 * recommended that application should get other services from this class only.
 * 
 * @author Rajesh Debnath
 *
 */
@JustForTheChallenge("Guice/SpringBoot")
public final class ServiceManager {

	private static final ServiceManager SINGLETON;
	
	static {
		final ConfigService config = new SystemPropertyConfigService();
		
		final boolean useUpper = config.getBoolean(ConfigKey.USE_UPPER_CASE, Boolean.TRUE);
		
		final UnaryOperator<String> operator;
		final CipherMode mode;
		if(useUpper) {
			operator = String::toUpperCase;
			mode = CipherMode.UPPER;
		} else {
			operator = String::toLowerCase;
			mode = CipherMode.LOWER;
		}
		
		final MessageProcessor<String> processor = new DefaultMessageProcessor(new SeasarDecipherAlgo(mode),
				k -> k.getEmblem().length());
		
		final Kingdom ruler = new Kingdom(operator.apply("SPACE"), operator.apply("GORILLA"));
		
		final Collection<Pair<Kingdom, MessageProcessor<String>>> kingdoms = Arrays.asList(
				Pair.of(ruler, processor),
				Pair.of(new Kingdom(operator.apply("LAND"), operator.apply("PANDA")), processor),
				Pair.of(new Kingdom(operator.apply("WATER"), operator.apply("OCTOPUS")), processor),
				Pair.of(new Kingdom(operator.apply("ICE"), operator.apply("MAMMOTH")), processor),
				Pair.of(new Kingdom(operator.apply("AIR"), operator.apply("OWL")), processor),
				Pair.of(new Kingdom(operator.apply("FIRE"), operator.apply("DRAGON")), processor));
		
		final KingdomRegistry registry = new KingdomRegistry(kingdoms, ruler);
		
		final MessageProvider<String> messageProvider = new FileInputMessageProvider(registry, new InputParser(operator));
		
		final int minAlliesToWin = kingdoms.size() / 2;
		final ResultEvaluator evaluator = new DefaultResultEvaluator(r -> r.getKingdomsWon().size() >= minAlliesToWin);
		
		SINGLETON = new ServiceManager(config, registry, messageProvider, evaluator);
	}
	
	private final AtomicBoolean initialized = new AtomicBoolean(false);
	
	private final ConfigService config;
	
	private final KingdomRegistry kingdomRegistry;
	
	private final MessageProvider<String> messageProvider;
	
	private final ResultEvaluator resultEvaluator;
	
	private ServiceManager(ConfigService config,
			KingdomRegistry registry,
			MessageProvider<String> messageProvider,
			ResultEvaluator resultEvaluator) {
		this.config = config;
		this.kingdomRegistry = registry;
		this.messageProvider = messageProvider;
		this.resultEvaluator = resultEvaluator;
	}
	
	/**
	 * Gets singleton instance of this class.
	 * 
	 * @return Singleton instance of this class.
	 */
	public static ServiceManager getInstance() {
		return SINGLETON;
	}
	
	/**
	 * To be called once before invoking other method. Once invoked, subsequent
	 * calls effectively does nothing.
	 * 
	 * @param source The source of the input messages.
	 */
	public void init(String source) {
		if(initialized.compareAndSet(false, true)) {
			messageProvider.load(source);
		}
	}
	
	/**
	 * Gets the configuration service.
	 * 
	 * @return The configuration service.
	 */
	public ConfigService getConfigService() {
		return config;
	}
	
	/**
	 * Gets the kingdom registry.
	 * 
	 * @return The kingdom registry.
	 */
	public KingdomRegistry getKingdomRegistry() {
		return kingdomRegistry;
	}
	
	/**
	 * Gets the input message provider.
	 * 
	 * @return The input message provider.
	 */
	public MessageProvider<String> getMessageProvider() {
		return messageProvider;
	}
	
	/**
	 * Get the result evaluator which is useful to evaluate a result and produce a
	 * printable output.
	 * 
	 * @return A result evaluator.
	 */
	public ResultEvaluator getResultEvaluator() {
		return resultEvaluator;
	}
	
}
