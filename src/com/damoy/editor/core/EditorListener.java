package com.damoy.editor.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.damoy.editor.input.Keys;

public class EditorListener implements ActionListener, KeyListener  {

	private Editor source;
	
	public EditorListener(Editor source) {
		this.source = source;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!processTextAction(e.getActionCommand())) {
			processFileAction(e.getActionCommand());
		}
	}
	
	private boolean processTextAction(String command) {
		JTextArea textArea = source.getTextArea();
		boolean processed = false;
		
		if(command.equals(source.getCutItem().getText())){
			textArea.cut();
			processed = true;
		} else if(command.equals(source.getCopyItem().getText())){
			textArea.copy();
			processed = true;
		} else if(command.equals(source.getPasteItem().getText())) {
			textArea.paste();
			processed = true;
		}
		
		return processed;
	}
	
	private void processFileAction(String command) {
		if(command.equals(source.getSaveItem().getText())) {
			save();
		} else if(command.equals(source.getPrintItem().getText())) {
			try {
				source.getTextArea().print();
			} catch (PrinterException e) {
				JOptionPane.showMessageDialog(source.getFrame(), e.getMessage()); 
			}
		} else if(command.equals(source.getOpenItem().getText())) {
			open();
		} else if(command.equals(source.getNewItem().getText())) {
			clearFile();
		} else if(command.equals(source.getExitItem().getText())) {
			System.exit(0);
		}
	}
	
	public void save() {
		JFileChooser chooser = new JFileChooser();
		int state = chooser.showSaveDialog(null);
		
		if(state == JFileChooser.APPROVE_OPTION) {
			File file;
			try {
				file = new File(chooser.getSelectedFile().getAbsolutePath());
				BufferedWriter w = new BufferedWriter(new FileWriter(file, false));
				w.write(source.getTextArea().getText());
				w.flush();
				w.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(source.getFrame(), e.getMessage());
			}
		} else {
			JOptionPane.showMessageDialog(source.getFrame(), "Operation cancelled.");
		}
	}
	
	public void open() {
		JFileChooser chooser = new JFileChooser();
		int state = chooser.showOpenDialog(null);
		
		if(state == JFileChooser.APPROVE_OPTION) {
			try {
				Scanner scanner = new Scanner(new File(chooser.getSelectedFile().getAbsolutePath()));
				StringBuilder sb = new StringBuilder();
				
				while(scanner.hasNextLine()){
					sb.append(scanner.nextLine());
					sb.append("\n");
				}
				
				scanner.close();
				source.getTextArea().setText(sb.toString());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void clearFile() {
		source.getTextArea().setText("");
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		Keys.keySet(e.getKeyCode(), true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Keys.keySet(e.getKeyCode(), false);
	}

}
