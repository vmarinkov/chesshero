package com.kt.game;

import java.util.Collection;

/**
 * Created by Toshko on 12/23/13.
 */
public class MovementSet
{
	// Vertical and horizontal
	public static final Position UP = new Position(0, 1);
	public static final Position UP2 = new Position(0, 2);
	public static final Position LEFT = new Position(-1, 0);
	public static final Position DOWN = new Position(0, -1);
	public static final Position DOWN2 = new Position(0, -2);
	public static final Position RIGHT = new Position(1, 0);

	// Diagonal
	public static final Position UP_LEFT = new Position(-1, 1);
	public static final Position DOWN_LEFT = new Position(-1, -1);
	public static final Position DOWN_RIGHT = new Position(1, -1);
	public static final Position UP_RIGHT = new Position(1, 1);

	// Knight
	public static final Position LEFT2_UP = new Position(-2, 1);
	public static final Position LEFT2_DOWN = new Position(-2, -1);
	public static final Position RIGHT2_UP = new Position(2, 1);
	public static final Position RIGHT2_DOWN = new Position(2, -1);
	public static final Position LEFT_UP2 = new Position(-1, 2);
	public static final Position LEFT_DOWN2 = new Position(-1, -2);
	public static final Position RIGHT_UP2 = new Position(1, 2);
	public static final Position RIGHT_DOWN2 = new Position(1, -2);

	private Collection<Position> set;
	private boolean single = false;

	public MovementSet(Collection<Position> set)
	{
		this.set = set;
	}

	public MovementSet(Collection<Position> set, boolean single)
	{
		this.set = set;
		this.single = single;
	}

	public Collection<Position> getSet()
	{
		return set;
	}

	public boolean isSingle()
	{
		return single;
	}
}