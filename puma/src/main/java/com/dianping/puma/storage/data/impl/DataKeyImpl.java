package com.dianping.puma.storage.data.impl;

import com.dianping.puma.storage.Sequence;
import com.dianping.puma.storage.data.DataKey;

public class DataKeyImpl implements DataKey {

	private Sequence sequence;

	public DataKeyImpl(Sequence sequence) {
		this.sequence = sequence;
	}

	@Override
	public long offset() {
		return 0;
	}

	@Override
	public void addOffset(long delta) {

	}

	public Sequence getSequence() {
		return sequence;
	}

	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}
}