package com.mpe85.grampa.rule;

@FunctionalInterface
public interface ValueSupplier<T> {
	
	T supply(ActionContext<T> context);
	
}
