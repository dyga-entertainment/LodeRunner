package MVC.View.Game;

import MVC.Model.Game.EntityModel;
import MVC.View.Menu.Component.ViewPanel;
import Utils.Math.Position2D;

import javax.swing.*;
import java.awt.*;

public class EntityView {

    EntityModel entityModel;

    public EntityView(){
        this.entityModel = new EntityModel();
    }

    public EntityView(EntityModel entityModel){
        this.entityModel = entityModel;
    }

    public void paint(Graphics g, Container im) {
        Position2D position = this.entityModel.getPosition();
        g.drawImage(this.entityModel.getSprite(), (int)position.getX(), (int)position.getY(), 50, 50, im) ;
    }
}
