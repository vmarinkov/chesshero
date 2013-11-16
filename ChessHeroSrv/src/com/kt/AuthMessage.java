package com.kt;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;

/**
 * Created with IntelliJ IDEA.
 * User: Toshko
 * Date: 11/16/13
 * Time: 1:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuthMessage extends Message
{
    private int action;
    private Credentials credentials;

    AuthMessage(int action, Credentials credentials) throws ChessHeroException
    {
        if (!Message.isActionValid(action))
        {
            throw new ChessHeroException(ChessHeroException.INVALID_ACTION_ERROR);
        }

        this.action = action;
        this.credentials = credentials;
    }

    public int getAction()
    {
        return action;
    }

    public Credentials getCredentials()
    {
        return credentials;
    }

    @Override
    public byte[] toData()
    {
        byte nameData[] = credentials.getName().getBytes();
        byte passData[] = credentials.getPass().getBytes();

        int bodyLen = 2 + 2 + nameData.length + 2 + passData.length; // Action + name length + name + pass length + pass

        ByteBuffer messageData = ByteBuffer.allocate(Message.HEADER_LENGTH + bodyLen);
        messageData.putShort((short)bodyLen); // Put header
        messageData.putShort((short)action); // Put action
        messageData.putShort((short)nameData.length); // Put name length
        messageData.put(nameData); // Put name
        messageData.putShort((short)passData.length); // Put pass length
        messageData.put(passData); // Put pass

        return messageData.array();
    }
}
