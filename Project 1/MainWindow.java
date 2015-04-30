import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.util.ArrayList;
import java.util.Random;

import java.lang.String;
import java.lang.Character;

public class MainWindow extends JFrame {
	
	private int remainingGuesses;
	private String wrongGuesses;
	private String word;
	private String visible;
	private ArrayList<String> guessed = new ArrayList<String>();
	private int numberHints;
	private char tempVisible[];
	private String tempGuess;
	private int hintLoc;
	private ArrayList<Integer> intGen = new ArrayList<Integer>();
	private Random rand = new Random();

	public MainWindow(String toGuess,int hints) {
		remainingGuesses = 10;
		wrongGuesses = "";
		word = toGuess;
		numberHints = hints;
		
		visible = "";

		for(int i = 0; i < word.length(); ++i) {
			visible += "_ ";
		}
		if(numberHints > 0)
		{
			tempVisible = visible.toCharArray();
			for(int i=0;i<numberHints;i++)
			{
				hintLoc = rand.nextInt(word.length());
				while(intGen.contains(hintLoc)){
					hintLoc = rand.nextInt(word.length());
				}
				intGen.add(hintLoc);
				tempVisible[2*hintLoc] = word.charAt(hintLoc);
				tempGuess = "" + word.charAt(hintLoc);
				guessed.add(tempGuess);
			}
			visible = new String(tempVisible);
		}

		JPanel corePanel = new JPanel();
		corePanel.setLayout(new BorderLayout());
		
		final JLabel status = new JLabel("You have "+remainingGuesses+" remaining", SwingConstants.CENTER);
		final JLabel wrong = new JLabel("Wrong guesses so far: "+wrongGuesses);
		final JLabel visibleLabel = new JLabel(visible, SwingConstants.CENTER);
		final JTextField input = new JTextField(); 
		
		JPanel southPanel = new JPanel(new GridLayout(4, 1));
		southPanel.add(status);
		southPanel.add(visibleLabel);
		southPanel.add(input);
		southPanel.add(wrong);
		
		corePanel.add(southPanel, BorderLayout.SOUTH);
		
		final HangmanFigure hf = new HangmanFigure();
		corePanel.add(hf, BorderLayout.CENTER);

		this.add(corePanel, BorderLayout.CENTER);
		
		input.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				String text = input.getText();
				
				if(text.length()  == 1 && text.matches("[a-z]")) {
					
					if(guessed.contains(text))
					{
						System.out.println("That letter has been guessed already!");
					}
					else{
						
						boolean guessFound = false;					
						guessed.add(text);
						
						for(int i = 0; i < word.length(); ++i) {
							if(text.charAt(0) == word.charAt(i)) {
								guessFound = true;
								
								String newVisible = "";
								for(int j = 0; j < visible.length(); ++j) {
									if(j == (i*2)) {
										newVisible += word.charAt(i);
									}
									else {
										newVisible += visible.charAt(j);
									}
								}
								visible = newVisible;
								visibleLabel.setText(visible);
							}
						}
						
						if(!guessFound) {
							if(--remainingGuesses >= 0) {
								status.setText("You have "+remainingGuesses+" guesses remaining");
								wrongGuesses += text+" ";
								wrong.setText("Wrong guesses so far: "+wrongGuesses);
								hf.set();
							}
							else {
								hf.set();
								status.setText("You lost: the word was "+word);
								input.setEnabled(false);
							}
						}
						else {
							String actualVisible = "";
							for(int i = 0; i < visible.length(); i+=2) {
								actualVisible += visible.charAt(i);
							}
							
							if(actualVisible.equals(word)) {
								status.setText("Congratulations, you have won!");
								input.setEnabled(false);
							}
						}
					
					}
				}
				else {
					System.out.println("Invalid input!");
				}
				
				input.setText("");
			}
			
		});
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	

	public static void main(String[] args) {
		new MainWindow("catastrophe",4);
	}
}
