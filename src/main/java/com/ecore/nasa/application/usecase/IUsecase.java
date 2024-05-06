package com.ecore.nasa.application.usecase;

public interface IUsecase<Input, Output> {
	Output execute(Input input);
}
