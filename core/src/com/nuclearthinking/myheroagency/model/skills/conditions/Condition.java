package com.nuclearthinking.myheroagency.model.skills.conditions;

/**
 * Created by Izonami on 03.08.2016.
 */
public abstract class Condition {

    private String _msg;
    private int _msgId;
    private boolean _addName = false;

    public final void setMessage(final String msg)
    {
        _msg = msg;
    }

    public final String getMessage()
    {
        return _msg;
    }

    public final void setMessageId(final int msgId)
    {
        _msgId = msgId;
    }

    public final int getMessageId()
    {
        return _msgId != 0 ? _msgId : 0;
    }

    public final void addName()
    {
        _addName = true;
    }

    public final boolean isAddName()
    {
        return _addName;
    }

}
