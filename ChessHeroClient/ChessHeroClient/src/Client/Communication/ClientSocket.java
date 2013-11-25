package Client.Communication;

import com.kt.ChessHeroException;
import com.kt.Message;
import com.kt.Utils;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: Toshko
 * Date: 11/23/13
 * Time: 2:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientSocket
{
    private Socket sock = null;

    ClientSocket(String address, int port) throws IOException
    {
        InetAddress addr = InetAddress.getByName(address);
        sock = new Socket(addr, port);
        sock.setKeepAlive(true);
        sock.setSoTimeout(0);
    }

    public boolean isConnected()
    {
        // isConnected returns true if the socket has ever been connected, meaning it will return true even after
        // closing the socket. Vice-versa, isClosed returns true only if the socket has ever been closed
        if (sock.isClosed())
        {
            return false;
        }

        return sock.isConnected();
    }

    public void disconnect() throws IOException
    {
        sock.close();
    }

    public void setTimeout(int millis) throws IOException
    {
        sock.setSoTimeout(millis);
    }

    public int getTimeout() throws IOException
    {
        return sock.getSoTimeout();
    }

    public Message readMessage() throws IOException, EOFException, ChessHeroException
    {
        // The first two bytes will be the body length
        byte headerData[] = readBytesWithLength(2); // Read the header
        short bodyLen = Utils.shortFromBytes(headerData, 0);
        byte bodyData[] = readBytesWithLength(bodyLen);

        return Message.fromData(bodyData);
    }

    private byte[] readBytesWithLength(int len) throws IOException, EOFException
    {
        InputStream stream = sock.getInputStream();
        int bytesRead = 0;
        byte data[] = new byte[len];

        do
        {   // The docs are ambiguous as to whether this will definitely try to read len or can return less than len
            // so just in case iterating until len is read or shit happens
            bytesRead = stream.read(data, 0, len);
            if (-1 == bytesRead)
            {
                throw new EOFException();
            }
        }
        while (bytesRead != len);

        return data;
    }

    public void writeMessage(Message message) throws IOException
    {
        byte messageData[] = message.toData();
        short bodyLen = (short)messageData.length;

        byte all[] = new byte[2 + bodyLen];
        Utils.bytesPutShort(all, bodyLen, 0);
        Utils.bytesPutBytes(all, messageData, 2);

        sock.getOutputStream().write(all);
    }
}
