package com.dianping.puma.api;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dianping.pigeon.remoting.invoker.config.annotation.Reference;
import com.dianping.puma.core.api.StateAckService;
import com.dianping.puma.core.model.ClientAck;
import com.dianping.puma.core.model.ServerAck;

@Service()
public class ClientAckService {
	@Reference(timeout = 1000)
	private StateAckService stateReporterService;
	
	public void pushClientAck() {
		stateReporterService.setClientAck(collectClientAck());
	}
	
	private ClientAck collectClientAck() {
		ClientAck clientAck = new ClientAck();
		return clientAck;
	}
}