package com.kt.game.chesspieces;

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

	public Rook(Position position, Player owner)
	{
		super(position, owner, set);
	}

	@Override
	public boolean isMoveValid()
	{
		return false;
	}
}
