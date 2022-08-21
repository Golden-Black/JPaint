package model;

import model.interfaces.IShape;
import model.interfaces.IUndoable;
import java.awt.*;
import java.util.Stack;

public class CommandHistory {
	private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();

	public static void add(IUndoable cmd) {
		undoStack.push(cmd);
		redoStack.clear();
	}
	
	public static boolean undo() {
		boolean result = !undoStack.empty();
		System.out.println("Undo from CommandHistory");
		System.out.println("1");
		if (result) {
			IUndoable c = undoStack.pop();
			redoStack.push(c);
			System.out.println("2");
			c.undo();
		}
		return result;
	}

	public static boolean redo() {
		boolean result = !redoStack.empty();
		if (result) {
			IUndoable c = redoStack.pop();
			undoStack.push(c);
			c.redo();
		}
		return result;
	}
}
