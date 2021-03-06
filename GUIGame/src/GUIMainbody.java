import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

class ButtonIcons//icons asset manager class that deal mostly with pictures, only one instance shall be created
{
	public ImageIcon Husky;
	public ImageIcon HuskyLarge;
	public Image DogeIcon;//awt libraries use Image instead of ImageIcon class as arguments 
	public ImageIcon RoadBlock;
	public ImageIcon DefaultBackground;
	public ImageIcon DogeVictory;
	public ImageIcon DogeFail;
	
	ButtonIcons()//static use, initialized only once to get the icon images read
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		this.DogeIcon = tk.createImage("./Doge.gif");
		this.DogeVictory = new ImageIcon("./DogeVictory.png");
		this.DogeFail = new ImageIcon("./DogeFail.png");
		this.HuskyLarge = new ImageIcon("./HuskyLarge.png");
		this.Husky = new ImageIcon("./Husky.png");
		this.RoadBlock = new ImageIcon("./RoadBlock.png");
		this.DefaultBackground = new ImageIcon("./Default.png");
	}
}

@SuppressWarnings("serial")
class GridBlock extends JButton//a block from the grid, very simple
{
	static final public ButtonIcons icons = new ButtonIcons();
	private int coordX;//Coordinate of the grid
	private int coordY;//TODO : are these two members really used?
	private int status;//Is it now a husky, a road block, or simply a background brick?
	
	GridBlock(int coordX, int coordY)//initialize the position of the block button in the grid
	{
		this.coordX = coordX;
		this.coordY = coordY;
		this.status = 0;
	}
	void updateStatus(int newStatus)//update its status, now it might be something else
	{
		this.status = newStatus;
		switch(this.status)
		{
		case 0:
			this.setIcon(icons.Husky);
			break;
		case 1:
			this.setIcon(icons.RoadBlock);
			break;
		case 2:
			this.setIcon(icons.DefaultBackground);
			break;
		}
	}
	int getStatus()
	{
		return this.status;
	}
}

public class GUIMainbody
{
	class AddRoadBlock implements ActionListener//Possibly that's the game logic's all about
	{
		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			GridBlock BlockPressed = (GridBlock)arg0.getSource();
			if(BlockPressed.getStatus() != 2)
				return;//It's not allowed to press the Husky or a roadblock! When somebody really does just do nothing
			
			if(HuskyPositionx == 0 || HuskyPositionx == GridSize - 1
					|| HuskyPositiony == 0 || HuskyPositiony == GridSize - 1)
			{
				//failed to capture the Husky! You lost this round!
				JOptionPane.showMessageDialog(Mainbody, "The Husky just reached the border,"
						+ " and you lost for letting him escape", "Oops!", JOptionPane.INFORMATION_MESSAGE,
						 GridBlock.icons.DogeFail);
				Reset();
				return;
			}
			
			BlockPressed.updateStatus(1);
			//now a roadblock is placed at the button pressed
			//Husky shall find its optimal escape course via BFS
			//since in this scenario BFS gives the shortest path
			
			ArrayList<Boolean> isTraversed = new ArrayList<Boolean>();
			//is the vertex traversed? We have to keep track of this
			for(int i = 0; i!=GridSize; ++i)
			{
				for(int j = 0; j!=GridSize; ++j)
				{
					isTraversed.add(false);
					//of course at the beginning it's not traversed yet
				}
			}
			
			ArrayList<Integer> ancestor = new ArrayList<Integer>();
			//recording its ancestors so that we may determine which box the Husky should go
			for(int i = 0; i!=GridSize; ++i)
			{
				for(int j = 0; j!=GridSize; ++j)
				{
					ancestor.add(i*GridSize + j);
					//When traversing begins, every vertex's ancestor's itself
				}
			}
			
			Queue<Integer> container = new LinkedList<Integer>();
			
			container.offer(HuskyPositionx*GridSize + HuskyPositiony);
			isTraversed.set(HuskyPositionx*GridSize + HuskyPositiony, true);
			int huskyNextStep = GridSize * GridSize;
			//an impossible value while traversing if the Husky can escape 
			//the value is unchanged if the Husky can't escape(roadblocks form a loop)
			
			//BFS loop goes here
			while(!container.isEmpty())
			{
				int current = container.poll();
				isTraversed.set(current, true);
				int row = current / GridSize;
				int col = current - GridSize*(current / GridSize);
				
				if(row == 0 || row == GridSize - 1 
				|| col == 0 || col == GridSize - 1)//the Husky finds that it's possible to escape!
				{
					huskyNextStep = current;
					while(true)//trace the ancestor of the block on the border to decide which way to go next
					{
						int itsAncestor = ancestor.get(huskyNextStep);
						if(itsAncestor == ancestor.get(itsAncestor))
							break;
						huskyNextStep = itsAncestor;
					}
					break;
				}
				
				if(row - 1 >= 0)
				{
					int northIndex = (row - 1)*GridSize + col;
					if(!isTraversed.get(northIndex) && Blocks.get(northIndex).getStatus() != 1)
					{
						isTraversed.set(northIndex, true);
						container.offer(northIndex);
						ancestor.set(northIndex, current);
					}
				}
				if(col - 1 >= 0)
				{
					int westIndex = row*GridSize + col - 1;
					if(!isTraversed.get(westIndex) && Blocks.get(westIndex).getStatus() != 1)
					{
						isTraversed.set(westIndex, true);
						container.offer(westIndex);
						ancestor.set(westIndex, current);
					}
				}
				if(row + 1 <= GridSize - 1)
				{
					int southIndex = (row + 1)*GridSize + col;
					if(!isTraversed.get(southIndex) && Blocks.get(southIndex).getStatus() != 1)
					{
						isTraversed.set(southIndex, true);
						container.offer(southIndex);
						ancestor.set(southIndex, current);
					}
				}
				if(col + 1 <= GridSize - 1)
				{
					int eastIndex = row*GridSize + col + 1;
					if(!isTraversed.get(eastIndex) && Blocks.get(eastIndex).getStatus() != 1)
					{
						isTraversed.set(eastIndex, true);
						container.offer(eastIndex);
						ancestor.set(eastIndex, current);
					}
				}
				//decide whether it should explore the four directions
			}
			
			if(huskyNextStep == GridSize*GridSize)
			{
				JOptionPane.showMessageDialog(Mainbody, "The Husky had nowhere to go, "
						+ "he had to surrender!", "Victory", JOptionPane.INFORMATION_MESSAGE,
						 GridBlock.icons.DogeVictory);
				//ah ha! the value hasn't changed, in which case Husky's now captured! You won!
				Reset();
			}
			else
			{
				Blocks.get(HuskyPositionx*GridSize + HuskyPositiony).updateStatus(2);
				HuskyPositionx = huskyNextStep / GridSize;
				HuskyPositiony = huskyNextStep - GridSize*(huskyNextStep / GridSize);
				Blocks.get(huskyNextStep).updateStatus(0);//Husky's gone to this button
			}
		}
	}
	
	private JFrame Mainbody;
	private GridLayout ChessGrid;
	private ArrayList<GridBlock> Blocks;
	public AddRoadBlock RoadBlocker = new AddRoadBlock();
	
	private int GridSize;
	private int HuskyPositionx;
	private int HuskyPositiony;
	
	public GUIMainbody(String Title)
	{
		this.Mainbody = new JFrame(Title);
		this.GridSize = 13;//13 * 13 Grid shall do it
		this.Blocks = new ArrayList<GridBlock>();
		
		InitializeGrid();
		InitializeWindow();
		InitializeHuskyPosition();
		InitializeRoadBlockPosition();
	}
	
	private void InitializeWindow()
	{
		Mainbody.setVisible(true);
		Mainbody.setSize(840, 840);
		Mainbody.setLocationRelativeTo(null);
		Mainbody.setIconImage(GridBlock.icons.DogeIcon);
		
		Mainbody.setResizable(false);
		Mainbody.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void InitializeGrid()//initialize the grid on start
	{
		ChessGrid = new GridLayout(GridSize, GridSize, 0, 0);
		Mainbody.setLayout(ChessGrid);
		for(int x = 0; x!=GridSize; ++x)
		{
			for(int y = 0; y!=GridSize; ++y)
			{
				Blocks.add(new GridBlock(x, y));
				UpdateGridStatus(x, y, 2);
			}
		}
		for(GridBlock i : Blocks)
		{
			Mainbody.add(i);
			i.addActionListener(RoadBlocker);
		}
	}
	
	public void InitializeHuskyPosition()//initialize the Husky at the center of the grid
	{
		int InitialCoordinate = this.GridSize / 2;
		HuskyPositionx = InitialCoordinate;
		HuskyPositiony = InitialCoordinate;
		this.UpdateGridStatus(HuskyPositionx, HuskyPositiony, 0);
	}
	
	public void InitializeRoadBlockPosition()//randomly generate roadblocks
	{
		Random randomCoord = new Random();
		for(int i = 0; i!= 5; ++i)//FIXME: magic number 5's present, how should I proceed
		{
			int x = randomCoord.nextInt(GridSize);
			int y = randomCoord.nextInt(GridSize);
			while(x == this.HuskyPositionx || y == this.HuskyPositiony)
			{
				x = randomCoord.nextInt(GridSize);
				y = randomCoord.nextInt(GridSize);
			}
			this.UpdateGridStatus(x, y, 1);
		}
	}
	
	public void UpdateGridStatus(int x, int y, int status)//update a button's status: what is it right now?
	{
		this.Blocks.get(x*this.GridSize + y).updateStatus(status);
	}
	
	public void Reset()//reset to its initial state
	{
		for(GridBlock i : Blocks)
		{
			i.updateStatus(2);
		}
		InitializeHuskyPosition();
		InitializeRoadBlockPosition();
	}
	
	public static void main(String[] Args)
	{
		JOptionPane.showMessageDialog(null, "One of our local Huskies is still at large"
				+ ", click on the buttons to place roadblocks to stop him! ", 
				"Game by Bowen", JOptionPane.WARNING_MESSAGE, GridBlock.icons.HuskyLarge);
		GUIMainbody mainBody = new GUIMainbody("Chase down the Husky");
	}
}
