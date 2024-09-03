package simpleJson.impl.services;

import simpleJson.api.Handler;
import simpleJson.api.ValidationService;
import simpleJson.impl.handlers.NotNullHandler;
import simpleJson.impl.handlers.NullObjectHandler;

public class ValidationServiceImpl implements ValidationService {

    private Handler notNullHandler = new NotNullHandler();
    private Handler notEmptyHandler = new NotNullHandler();
    private Handler invalidStructureHandler = new NotNullHandler();
    private Handler nullHandler = new NullObjectHandler();
    private Handler chain;

    public ValidationServiceImpl() {
        this.chain = (json, next) ->
                notNullHandler.handle(json,
                        (j1, n1) -> notEmptyHandler.handle(j1,
                                (j2, n2) -> invalidStructureHandler.handle(j2,
                                        (j3, n3) -> nullHandler.handle(j3, null))));
    }

    @Override
    public void validate(String json) {
        chain.handle(json, null);
    }
}
