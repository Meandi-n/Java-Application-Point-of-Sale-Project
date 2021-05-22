package graphicalinterface.customPanels;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

import graphicalinterface.FileSelectorInterface;
import graphicalinterface.GetSystemDetails;
import graphicalinterface.GraphicalInterface;
import graphicalinterface.customcomponents.BackgroundPanel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class StartSessionPage extends BackgroundPanel
{
	private main.Session currentSession;
	private GraphicalInterface g;
	public StartSessionPage(main.Session session, GraphicalInterface g)
	{
		super("Session Page");
		this.currentSession = session;
		this.g = g;
		this.createInterface();
		if(this.currentSession == null)
			System.out.println("NULL");
	}
	
	private void createInterface()
	{
		/* adding exit to toolkit */
		graphicalinterface.customcomponents.DetailButton exitButton = new graphicalinterface.customcomponents.DetailButton("Exit");
		exitButton.addActionListener(e -> this.exitProgram());
		//formatting exit button	
		super.toolBar.add(exitButton);
		
		/* creating buttons for starting a new shift, or continuing a shift */
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		graphicalinterface.customcomponents.AttentionButton newShiftButton = new graphicalinterface.customcomponents.AttentionButton("Start New Shift");
		graphicalinterface.customcomponents.StyledButton continueShiftButton = new graphicalinterface.customcomponents.StyledButton("Continue Shift");
		
		newShiftButton.addActionListener(e -> this.newShiftEvent());
		continueShiftButton.addActionListener(e -> this.continueShiftEvent());
		
		newShiftButton.setToolTipText("Start a new shift from scratch.");
		continueShiftButton.setToolTipText("(Advanced) Continue a shift from a serialsied shift object file.");
		
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0; c.gridy = 0;
		c.weighty = 1; c.weightx = 1;
		buttonPanel.add(continueShiftButton, c);
		
		c.gridx = 1; c.gridy = 0;
		c.weighty = 1; c.weightx = 2;
		buttonPanel.add(newShiftButton, c);
		
		
		buttonPanel.setOpaque(false);
	
		/* adding all panels to container of frame */
		this.add(buttonPanel);
	}
	
	private void newShiftEvent()
	{
		this.currentSession.newShift();
		this.setVisible(false);
		g.setCurrentPanel(new ShiftOperationsPage(this.currentSession, this.g));
		g.updateFrame();
	}
	
	private void continueShiftEvent()
	{
		File file = graphicalinterface.FileSelectorInterface.fileChooser();
		String path = file.getAbsolutePath();
		this.currentSession.continueShift(path);

		this.setVisible(false);
		g.currentPanel = new ShiftOperationsPage(this.currentSession, this.g);
		g.updateFrame();
	}
	
	private void exitProgram()
	{
		g.frame.dispose();
	}
}
