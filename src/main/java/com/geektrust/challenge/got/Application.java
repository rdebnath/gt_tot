package com.geektrust.challenge.got;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.geektrust.challenge.got.core.KingdomRegistry;
import com.geektrust.challenge.got.core.MessageProcessor;
import com.geektrust.challenge.got.core.ServiceManager;
import com.geektrust.challenge.got.model.Kingdom;
import com.geektrust.challenge.got.model.Message;
import com.geektrust.challenge.got.model.Result;
import com.geektrust.challenge.got.util.Pair;

public class Application {

	private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
	
	public static void main(String[] args) {
		try {
			// Initialize Service Manager
			ServiceManager manager = ServiceManager.getInstance();
			manager.init(args[0]);

			// Read the messages from input file and prepare to send them
			List<Pair<Kingdom, Message<String>>> messages = manager.getMessageProvider().getMessages();
			KingdomRegistry kingdomRegistry = manager.getKingdomRegistry();
			Kingdom ruler = kingdomRegistry.getRuler();

			// Send messages to the respective Kingdoms and print the result
			String resultStr = manager.getResultEvaluator().evaluate(sendMessages(ruler, messages, kingdomRegistry));
			
			// Print the result
			System.out.println(resultStr);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception occured, exiting application", e);
		}
	}

	private static Result sendMessages(Kingdom ruler, List<Pair<Kingdom, Message<String>>> messages,
			KingdomRegistry kingdomRegistry) {
		List<Kingdom> list = messages.stream()
			.map(pair -> getResponse(ruler, pair, kingdomRegistry))
			.filter(Optional::isPresent)
			.map(Optional::get)
			.collect(Collectors.toList());

		return Result.newBuilder()
				.setRuler(ruler)
				.setKingdomsWon(list)
				.build();
	}

	private static Optional<Kingdom> getResponse(Kingdom ruler, Pair<Kingdom, Message<String>> message,
			KingdomRegistry kingdomRegistry) {
		Optional<Pair<Kingdom, MessageProcessor<String>>> kingdomProcessor = kingdomRegistry
				.getKingdomProcessorPairByName(message.getLeft().getName());
		if (kingdomProcessor.isPresent()) {
			return kingdomProcessor.get().getRight().sendMessage(ruler, message);
		}
		return Optional.empty();
	}

	private Application() {
	}

}
