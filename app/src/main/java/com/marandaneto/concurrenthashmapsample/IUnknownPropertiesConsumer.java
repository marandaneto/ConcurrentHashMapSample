package com.marandaneto.concurrenthashmapsample;

import java.util.Map;

public interface IUnknownPropertiesConsumer {

    void acceptUnknownProperties(Map<String, Object> unknown);
}
