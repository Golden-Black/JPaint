package model;

import model.persistence.ApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.*;

public class ShapeInfo {
    public Color primary;
    public Color secondary;
    public ShapeShadingType shapeShadingType;

    public ShapeInfo(Color primary, Color secondary, ShapeShadingType shapeShadingType) {
        this.primary = primary;
        this.secondary = secondary;
        this.shapeShadingType = shapeShadingType;
    }
}
