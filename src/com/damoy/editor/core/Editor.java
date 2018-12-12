package com.damoy.editor.core;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

public class Editor {

	private JTextArea textArea;
	private JFrame frame;
	private JMenuBar menuBar;
	
	private JMenu fileMenu;
	private JMenuItem newItem;
	private JMenuItem openItem;
	private JMenuItem saveItem;
	private JMenuItem printItem;
	private JMenuItem exitItem;
	
	private JMenu editMenu;
	private JMenuItem cutItem;
	private JMenuItem copyItem; 
	private JMenuItem pasteItem; 
	
	private EditorListener listener;

	public Editor() {
		super();
	}
	
	public void start() {
		build();
	}
	
	private void build() {
		frame = new JFrame("Editor");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.pack();
		
		textArea = new JTextArea();
		menuBar = new JMenuBar();
		listener = new EditorListener(this);
		frame.addKeyListener(listener);
		
		buildFileMenu();
		buildEditMenu();
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		
		frame.setJMenuBar(menuBar);
		frame.add(textArea);
		frame.setSize(400, 400);
		frame.setVisible(true);
		
		decorate();
	}
	
	private void buildFileMenu() {
		fileMenu = new JMenu("File");
		newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        printItem = new JMenuItem("Print");
        exitItem = new JMenuItem("Exit");
  
        newItem.addActionListener(listener);
        openItem.addActionListener(listener);
        saveItem.addActionListener(listener);
        printItem.addActionListener(listener);
        exitItem.addActionListener(listener);
  
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(printItem);
        fileMenu.add(exitItem);
	}
	
	private void buildEditMenu() {
		editMenu = new JMenu("Edit");
		
		cutItem = new JMenuItem("Cut"); 
        copyItem = new JMenuItem("Copy"); 
        pasteItem = new JMenuItem("Paste"); 
  
        cutItem.addActionListener(listener); 
        copyItem.addActionListener(listener); 
        pasteItem.addActionListener(listener); 
        
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
	}
	
	private void decorate() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			MetalLookAndFeel.setCurrentTheme(new OceanTheme());
		} catch (Exception e) {
		}
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JMenu getFileMenu() {
		return fileMenu;
	}

	public JMenuItem getNewItem() {
		return newItem;
	}

	public JMenuItem getOpenItem() {
		return openItem;
	}

	public JMenuItem getSaveItem() {
		return saveItem;
	}

	public JMenuItem getPrintItem() {
		return printItem;
	}

	public JMenu getEditMenu() {
		return editMenu;
	}

	public JMenuItem getCutItem() {
		return cutItem;
	}

	public JMenuItem getCopyItem() {
		return copyItem;
	}

	public JMenuItem getPasteItem() {
		return pasteItem;
	}
	
	public JMenuItem getExitItem() {
		return exitItem;
	}

}
