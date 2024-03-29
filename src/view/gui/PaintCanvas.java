package view.gui;

import model.ShapeList;
import model.ShapeShadingType;
import model.commands.ClickHandler;
import model.interfaces.IShape;
import view.interfaces.PaintCanvasBase;

import javax.swing.JComponent;
import java.awt.*;

public class PaintCanvas extends PaintCanvasBase {

    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2d = (Graphics2D)g;

        if(ShapeList.getCanvas().size() > 0){
            for(int i = 0; i < ShapeList.getCanvas().size(); ++i){

                ShapeShadingType shadingType = ShapeList.getShapeInfoList().get(i).shapeShadingType;

                if(shadingType.equals(ShapeShadingType.FILLED_IN)){
                    graphics2d.setColor(ShapeList.getShapeInfoList().get(i).primary);
                    graphics2d.fill(ShapeList.getCanvas().get(i));

                }else if(shadingType.equals(ShapeShadingType.OUTLINE)){
                    Stroke stroke = new BasicStroke(4);
                    graphics2d.setStroke(stroke);
                    graphics2d.setColor(ShapeList.getShapeInfoList().get(i).secondary);
                    graphics2d.draw(ShapeList.getCanvas().get(i));

                }else if(shadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)){
                    graphics2d.setColor(ShapeList.getShapeInfoList().get(i).primary);
                    graphics2d.fill(ShapeList.getCanvas().get(i));

                    Stroke stroke = new BasicStroke(4);
                    graphics2d.setStroke(stroke);
                    graphics2d.setColor(ShapeList.getShapeInfoList().get(i).secondary);
                    graphics2d.draw(ShapeList.getCanvas().get(i));
                }else throw new Error();

                graphics2d.draw(ShapeList.getCanvas().get(i));
            }
        }

        // Draw all shapes here

//        // For example purposes only; remove all lines below from your final project.
//        graphics2d.setColor(Color.GREEN);
//        graphics2d.fillRect(12, 13, 200, 400);
//
//        // Outlined rectangle
//        graphics2d.setStroke(new BasicStroke(5));
//        graphics2d.setColor(Color.BLUE);
//        graphics2d.drawRect(12, 13, 200, 400);
//
//        // Selected Shape
//        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
//        graphics2d.setStroke(stroke);
//        graphics2d.setColor(Color.BLACK);
//        graphics2d.drawRect(7, 8, 210, 410);
    }
}
