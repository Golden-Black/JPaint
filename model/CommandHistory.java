package model;

import model.interfaces.IShape;
import model.interfaces.IUndoable;

import java.awt.*;
import java.util.Stack;

public class CommandHistory {
	private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
	private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();

	public static final Stack<IShape> onShapeList = new Stack<>();

	public static void add(IUndoable cmd) {
		undoStack.push(cmd);
		redoStack.clear();
	}

	public static void addShape(IShape shape){
		onShapeList.add(shape);
	}
	
	public static boolean undo() {
		boolean result = !undoStack.empty();
		System.out.println("Undo from CommandHistory");
		if (result) {
			IUndoable c = undoStack.pop();
			redoStack.push(c);
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
