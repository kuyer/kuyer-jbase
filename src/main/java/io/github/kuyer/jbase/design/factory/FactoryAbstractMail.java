package io.github.kuyer.jbase.design.factory;

public class FactoryAbstractMail implements FactoryAbstract {

	@Override
	public SendApi produce() {
		return new SendByMail();
	}

}
