package sample.controller;

import java.awt.Point;
import java.awt.Polygon;

import sample.model.Model;
import sample.view.Application;
import junit.framework.TestCase;

public class TestCreatePolygon extends TestCase {
	
	Application app;
	Model model;
	
	@Override
	protected void setUp() {
		model = new Model();
		app = new Application (model);
		UpdateMenu.updateMenu(app, model);
		app.setVisible(true);
	}
	
	@Override 
	protected void tearDown() {
		app.dispose();
	}
	
	public void testFromScratch() {
		assertFalse (app.getUndoMenuItem().isEnabled());
		AddPointController apc = new AddPointController(app, model);
		apc.addPoint(new Point (10, 10));
		assertEquals ("Remove Last Point", app.getUndoMenuItem().getText());
		
		apc.addPoint(new Point (100, 10));
		apc.addPoint(new Point (50, 50));
		Polygon poly = model.getSelected().get();
		
		CompletePolygonController cpc = new CompletePolygonController(app, model);
		cpc.complete();
		assertEquals ("Remove Last Polygon", app.getUndoMenuItem().getText());
		
		assertEquals (3, poly.npoints);
		assertEquals (new Point (10,10), new Point(poly.xpoints[0], poly.ypoints[0]));
	}
}
