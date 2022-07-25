package controller;

import model.UndoCommand;
import model.interfaces.IApplicationState;
// import model.interfaces.insIApplicationState;
import view.EventName;
import view.interfaces.IEventCallback;
import view.interfaces.IUiModule;

import java.io.IOException;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        UndoCommand undoCommand = new UndoCommand();

        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, applicationState::setActiveStartAndEndPointMode);
//        uiModule.addEvent(EventName.UNDO, applicationState::undo);
//        uiModule.addEvent(EventName.REDO, applicationState::redo);

        uiModule.addEvent(EventName.UNDO, () -> {
            try {
                undoCommand.run();
            } catch (IOException
                    e) {
                e.printStackTrace();
            }
        });

        uiModule.addEvent(EventName.UNDO, () -> {
            try {
                undoCommand.run();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
