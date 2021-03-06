package agents.recommender;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import model.Request;
import agents.recommender.behaviour.NdRequestData;

public class NdRecommenderAgent extends RecommenderAgent {

	private static final long serialVersionUID = 21L;

	@Override
	protected void setup() {

		this.agentName = "level-recommender";
		super.setup();
		
	}

	@Override
	public Behaviour recommenderToDataBehaviour(AID dataAgent, Request request, ACLMessage msgFromStudent) {
		
		return new NdRequestData(dataAgent, request, msgFromStudent);
		
	}
	
}
