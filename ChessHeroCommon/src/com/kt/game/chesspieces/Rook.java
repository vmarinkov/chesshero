package com.kt.game.chesspieces;

import com.kt.game.Color;
import com.kt.game.MovementSet;
import com.kt.game.Player;
import com.kt.game.Position;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Toshko on 12/23/13.
 */
public class Rook extends ChessPiece
{
	static MovementSet set = new MovementSet(new ArrayList<Position>(Arrays.asList(
			MovementSet.UP, MovementSet.LEFT, MovementSet.DOWN, MovementSet.RIGHT
	)));

	private boolean moved = false;

	public Rook(Position position, Player owner, Color color)
	{
		super(position, owner, color, set);
	}

	@Override
	public void setPosition(Position newPos)
	{
		super.setPosition(newPos);
		moved = true;
	}

	public boolean hasMoved()
	{
		return moved;
	}

	@Override
	public boolean isMoveValid(Position pos, boolean take)
	{
		return position.isHorizontalOrVerticalTo(pos);
	}

	@Override
	public String toString()
	{
		return (Color.WHITE == color ? "R" : "r");
	}
}
