package event;

import java.util.EventObject;

import operation.AbstractOperation;
/*
* 事件类定义了操作的类型
* */
public abstract class AbstractEvent extends EventObject {

    AbstractEvent(Object obj){
        super(obj);
    }

    public abstract AbstractOperation getOperation();
}
