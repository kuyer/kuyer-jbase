package io.github.kuyer.jbase.design.factory;

public class FactoryAbstractSms implements FactoryAbstract {

	@Override
	public SendApi produce() {
		return new SendBySms();
	}

}
