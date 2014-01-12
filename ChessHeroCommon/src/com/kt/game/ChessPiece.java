package com.kt.game;

/**
 * Created by Toshko on 12/23/13.
 */
public abstract class ChessPiece
{
	public class Tag
	{
		public static final byte PAWN = 0;
		public static final byte ROOK = 1;
		public static final byte KNIGHT = 2;
		public static final byte BISHOP = 3;
		public static final byte QUEEN = 4;
		public static final byte KING = 5;
	}

	public static ChessPiece chessPieceFromData(byte data[])
	{
		if (data.length != 2)
		{
			return null;
		}

		byte meta = data[0]; // First byte contains the piece tag, color and whether it has moved
		byte tag = (byte)(meta & 0x7); // Take the first 3 bits

		boolean white = (meta & 0x8) != 0; // 4th bit is 1 for white 0 for black
		boolean moved = (meta & 0x10) != 0; // 5th bit signifies if the piece has moved

		Position position = Position.positionFromData(data[1]); // Second byte contains position

		if (null == position)
		{
			return null;
		}

		Color color = (white ? Color.WHITE : Color.BLACK);
		ChessPiece piece = aChessPiece(tag, position, color);

		if (piece != null)
		{
			piece.moved = moved;
		}

		return piece;
	}

	public static ChessPiece aChessPiece(int tag, Position position, Color color)
	{
		switch (tag)
		{
			case Tag.PAWN: 		return new Pawn(position, color);
			case Tag.ROOK: 		return new Rook(position, color);
			case Tag.KNIGHT: 	return new Knight(position, color);
			case Tag.BISHOP: 	return new Bishop(position, color);
			case Tag.QUEEN: 	return new Queen(position, color);
			case Tag.KING: 		return new King(position, color);
			default: 			return null;
		}
	}

	protected Position position;
	protected Color color;
	private Player owner = null;
	private byte tag;
	private MovementSet movementSet;

	private boolean moved = false;

	public ChessPiece(byte tag, Position position, Color color, MovementSet movementSet)
	{
		this.tag = tag;
		this.position = position;
		this.color = color;
		this.movementSet = movementSet;
	}

	public Position getPosition()
	{
		return position;
	}

	void setPosition(Position newPos)
	{
		position = newPos;
		moved = true;
	}

	public Color getColor()
	{
		return color;
	}

	public Player getOwner()
	{
		return owner;
	}

	void setOwner(Player owner)
	{
		this.owner = owner;
	}

	public MovementSet getMovementSet()
	{
		return movementSet;
	}

	public byte getTag()
	{
		return tag;
	}

	public boolean hasMoved()
	{
		return moved;
	}

	protected abstract boolean isMoveValid(Position pos, boolean take);

	public byte[] toData()
	{
		byte meta = tag;

		if (Color.WHITE == color)
		{
			meta |= 1 << 3;
		}
		if (moved)
		{
			meta |= 1 << 4;
		}

		return new byte[] {meta, position.toData()};
	}
}
