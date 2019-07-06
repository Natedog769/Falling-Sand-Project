/* A. Nate Mendoza
 * 
 * the new and improved particles project all previous bugs have been fixed with the wrapping 
 * 
 */
import java.awt.*;
import java.util.*;

public class SandLab{
  static final int MAX_ROWS = 180;  
  static final int MAX_COLS = 220;  
  
  static final String FILE_NAME     = "SandLabFileTesting.txt";  //This is the name of the input file.
  static final String NEW_FILE_NAME = "SandLabFileTesting.txt";  //This is the name of the file you are saving.
  
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  
  public static final int SAND = 2;
  public static final int WATER = 3;
  public static final int OIL = 4;
  public static final int ACID = 5;
  public static final int VAPOR = 6;
  public static final int FIRE = 7;
  public static final int SMOKE = 8;
  public static final int GENERATOR = 9;
  public static final int DESTRUCTOR = 10;
  public static final int SAVEFILE  = 11;
  public static final int LOADFILE = 12;
  public static final int CLEAR = 13;
  
  //do not add any more fields
  private int[][] sandGrid;
  private LabDisplay display;
  
  //---------------------------------------------------------------------------------------------------------
  
  public static void main(String[] args){
    System.out.println("================= Starting Program =================");
    
    SandLab lab = new SandLab(MAX_ROWS, MAX_COLS);
    lab.run();
  }
  
  //SandLab constructor 
  public SandLab(int numRows, int numCols){
    String[] names = new String[14]; 
    
    names[EMPTY]     = "Empty";
    names[METAL]     = "Metal";
    names[SAND]      = "Sand";
    names[WATER]     = "Water";
    names[OIL]       = "Oil";
    names[ACID]      = "Acid";
    names[VAPOR]     = "Vapor";
    names[FIRE]      = "Fire";
    names[SMOKE]     = "Smoke";
    names[GENERATOR] = "Generator";
    names[DESTRUCTOR]= "Destructor";
    names[SAVEFILE]  = "SaveFile";
    names[LOADFILE]  = "LoadFile";
    names[CLEAR]     = "Clear";
    
    
    display = new LabDisplay("SandLab", numRows, numCols, names);
    sandGrid = new int [numRows] [numCols];
    
    
    if (FILE_NAME != "") {
      System.out.println("loading " + FILE_NAME);
      sandGrid = SandLabFiles.readFile(FILE_NAME);   //uncomment this later to save your file...
    } 
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool){
    
    //insert code here 
    if (tool == EMPTY){
      sandGrid[row][col] = EMPTY;
    }
    else if (tool == METAL){
      sandGrid[row][col] = METAL;
    }      
    else if (tool == SAND){
      sandGrid[row][col] = SAND;
    }
    else if (tool == WATER){
      sandGrid[row][col] = WATER;        
    }
    else if (tool == OIL){
      sandGrid[row][col] = OIL;
    }   
    else if (tool == ACID){
      sandGrid[row][col] = ACID;
    }
    else if (tool == VAPOR){
      sandGrid[row][col] = VAPOR;
    }
    else if (tool == FIRE){
      sandGrid[row][col] = FIRE;
    }
    else if (tool == SMOKE){
      sandGrid[row][col] = SMOKE;
    }
    else if (tool == GENERATOR){
      sandGrid[row][col] = GENERATOR;
    }
    else if (tool == DESTRUCTOR){
      sandGrid[row][col] = DESTRUCTOR;
    }
    else if (tool == SAVEFILE) {
      SandLabFiles.writeFile(sandGrid, FILE_NAME);
    }
    else if (tool == LOADFILE) {
      sandGrid = SandLabFiles.readFile(FILE_NAME);
    }
    else if (tool == CLEAR) {
      sandGrid = new int [MAX_ROWS][MAX_COLS];
    }
    
  }
  
  //copies each element of grid into the display
  public void updateDisplay(){
    
    for (int r = 0; r < MAX_ROWS; r++ ){
      for (int c = 0; c < MAX_COLS; c++){
        if (sandGrid[r][c] == EMPTY)
          display.setColor(r,c,Color.black);
        else if (sandGrid[r][c] == METAL)
          display.setColor(r,c,Color.gray);
        else if (sandGrid[r][c] == SAND)
          display.setColor(r,c,Color.yellow);
        else if (sandGrid[r][c] == WATER)
          display.setColor(r,c,Color.blue);
        else if (sandGrid[r][c] == OIL)
          display.setColor(r,c,Color.orange);
        else if (sandGrid[r][c] == ACID)
          display.setColor(r,c,Color.green);
        else if (sandGrid[r][c] == VAPOR)
          display.setColor(r,c,Color.cyan);
        else if (sandGrid[r][c] == FIRE)
          display.setColor(r,c,Color.red);
        else if (sandGrid[r][c] == SMOKE)
          display.setColor(r,c,Color.white);
        else if (sandGrid[r][c] == GENERATOR)
          display.setColor(r,c,Color.lightGray);
        else if (sandGrid[r][c] == DESTRUCTOR)
          display.setColor(r,c,Color.darkGray);
        
        
        
        
      }
    }
  }// end of updateDisplay
  
  
  //called repeatedly.
  //causes one random particle to maybe do something.
  public void step(){
    
    //insert code here
    //make a random number
    Random randomNum = new Random();
    
    int rX = randomNum.nextInt(MAX_ROWS);
    int cY = randomNum.nextInt(MAX_COLS);
    int randMovement = randomNum.nextInt(11);
    
    if (sandGrid[rX][cY] == SAND) {//if the particle is sand then
      moveSandParticle(rX, cY);        
    }
    else if (sandGrid[rX][cY] == WATER){
      moveWaterParticle(rX, cY);
    }
    else if (sandGrid[rX][cY] == OIL){
      moveOilParticle(rX, cY);
    }
    else if (sandGrid[rX][cY] == ACID){
      moveAcidParticle(rX, cY);
    }
    else if (sandGrid[rX][cY] == VAPOR){
      moveAirParticle(VAPOR, rX, cY);
    }
    else if (sandGrid[rX][cY] == FIRE){
      moveAirParticle(FIRE, rX, cY);
    }
    else if (sandGrid[rX][cY] == SMOKE){
      moveAirParticle(SMOKE, rX, cY);
    }
    else if (sandGrid[rX][cY] == GENERATOR){
      generateParticles(rX, cY);
    }
    else if (sandGrid[rX][cY] == DESTRUCTOR){
      destroyParticlesAllSides(rX, cY);
    }
    
  }
  
  void destroyParticlesAllSides(int row, int col)
  {
    // if the top isnt empty then it will make it empty
    if (sandGrid[row-1][col] != EMPTY)
      sandGrid[row-1][col] = EMPTY;
    // if the bottom isnt empty then it will make it empty
    if (sandGrid[row+1][col] != EMPTY)
      sandGrid[row+1][col] = EMPTY;
    //if the left side isnt empty it will make it empty
    if (sandGrid[row][col-1] != EMPTY)
      sandGrid[row][col-1] = EMPTY;
    // if the right side isnt empty it will make it empty
    if (sandGrid[row][col+1] != EMPTY)
      sandGrid[row][col+1] = EMPTY;
      
  }
  
  //destroying particles and making a new one
  void destroyParticlesBelow(int destructiveParticle, int destroyableParticle, int creatableParticle, int row, int col)
  {
    int particleBelow = getParticleOnBottom(row, col);
    
    if (particleBelow == destroyableParticle)
    {
      sandGrid[row+1][col] = destructiveParticle;
      sandGrid[row][col] = creatableParticle;
    }
    
  }
  
  void destroyParticlesOnTop(int destructiveParticle, int destroyableParticle, int creatableParticle, int row, int col)
  {
    int particleOnTop = getParticleOnTop(row, col);
    
    if (particleOnTop == destroyableParticle)
    {
      sandGrid[row-1][col] = creatableParticle;
      sandGrid[row][col] = destructiveParticle;
    }
  }
  
  void destroyParticlesOnSides(int destructiveParticle, int destroyableParticle, int creatableParticle, int row, int col)
  {
    int rightParticle = getParticleToRight(row, col);
    int leftParticle = getParticleToLeft(row, col);
    
    if (rightParticle == destroyableParticle)
    {
      sandGrid[row][col+1] = destructiveParticle;
      sandGrid[row][col] = creatableParticle;
    }
    if (leftParticle == destroyableParticle)
    {
      sandGrid[row][col-1] = destructiveParticle;
      sandGrid[row][col] = creatableParticle;
    }
    
  }
  
  void generateParticles(int row, int col)
  {
    //this module will take the particle on top and bottom as a local variable
    int topParticle = getParticleOnTop(row, col);
    int bottomParticle = getParticleOnBottom(row, col);
    //if the top isnt empty and isnt metal and isnt another generator and the bottom is empty it will take the
    // top particle and make another one below it for the particles that are falling down
    if (topParticle != EMPTY && topParticle != METAL && topParticle != GENERATOR && bottomParticle == EMPTY)
      sandGrid[row+1][col] = topParticle;
    // if the bottom isnt empty or metal or another generator and the top is empty then it will
    // create the particle thats below and put it up top. for the air particles that move up
    if (bottomParticle != EMPTY && bottomParticle != METAL && bottomParticle != GENERATOR && topParticle == EMPTY)
      sandGrid[row-1][col] = bottomParticle;
    
  }
  
  void liquidMovement(int particle, int[]swappableParticles, int rX, int cY, boolean bubbleEffect)
  {
    //this module is to simulate constant liquid movement based on a random number
    int dice = getRandomNumber(0, 10);
    int rightParticle = getParticleToRight(rX, cY);
    int leftParticle = getParticleToLeft(rX, cY);
    
    if (dice > 5)
    {//if the rng is greater than 5 it will move to the right if it can swap with it
     //such as water and oil will move if they are beside each other or water and empty space will move
     //with this is will spread and level the water out.
      if (!isInLastColumn(rX, cY))
      {//this check is to see if it is in the last column if it isnt then it moves right but if it is it will wrap to the left side
        if (isRightSwappable(rightParticle, swappableParticles, rX, cY ))
        {
          moveRight(particle, rightParticle, rX, cY);
        }
      }
      else
      {
        wrapParticleRight(particle, swappableParticles, rX, cY);
      }
    }
    else if (dice < 5)
    {
      //if the rng is 5 or less then it will check to see if it needs to wrap to the otherside if not then it will move to the left
      if (!isInFirstColumn(rX, cY))
      {
        
        if (isLeftSwappable(leftParticle, swappableParticles, rX, cY ))
        {
          moveLeft(particle, leftParticle, rX, cY);
        }
      }
      else
        wrapParticleLeft(particle, swappableParticles, rX, cY);
    }
    else if (dice == 5 && bubbleEffect)
    {      
      if (getParticleOnTop(rX, cY) == EMPTY)
      {
        moveUp(particle, getParticleOnTop(rX, cY), rX , cY);
      }
    }
  }
  
  void moveAirParticle(int particle, int rX, int cY)
  {
    int[] swappableParticles = {EMPTY, OIL, WATER, SAND};
    
    if (!isInTopRow(rX, cY))
    {//if it is not in the last row it will check to see if the bottom is empty
      int particleOnTop = getParticleOnTop(rX, cY);
      
      if (isTopParticleSwappable(particleOnTop, swappableParticles, rX, cY))
      {//if the bottom is empty it willmove down
        moveUp(particle, particleOnTop, rX, cY);
      }
      else if (!isTopParticleSwappable(VAPOR, swappableParticles, rX, cY))
      {
        int dice = getRandomNumber(1, 10);
        
        int rightParticle = getParticleToRight(rX, cY);
        int leftParticle = getParticleToLeft(rX, cY);
        
        if (dice > 5 && isRightSwappable(rightParticle, swappableParticles, rX, cY))
        {
          moveRight(particle, rightParticle, rX, cY);
          
        }
        else if (dice < 5 && isLeftSwappable(leftParticle, swappableParticles, rX, cY))
        {
          moveLeft(particle, leftParticle, rX, cY);
        }
        else if (dice == 5)
        {
          destroyParticle(rX, cY);
        }
      }
    }
    else if (isInTopRow(rX, cY))
    {//if it is in the last row it will wrap to the top and that module will check to see if the top is empty 
     //if it is then it will wrap if not then it will stay put
      wrapParticleToBottom(particle, swappableParticles, rX, cY);
    } 
  }// end of air particle
  
  void moveAcidParticle(int rX, int cY)
  {
    int[] swappableParticles = {EMPTY, WATER, OIL, SAND};
    boolean bubbles = true;
    
    if (!isInLastRow(rX, cY))
    {//if it is not in the last row it will check to see if the bottom is empty
      int particleBelow = getParticleOnBottom(rX, cY);
      if (isBottomSwappable(particleBelow, swappableParticles, rX, cY))
      {//if the bottom is empty it willmove down but the acid will detroy particles and create a new air particle
        //acid and water will make vapor and acid and oil will make fire
        if (particleBelow == EMPTY)
          moveDown(ACID, particleBelow, rX, cY);
        if (particleBelow == WATER)
          destroyParticlesBelow(ACID, particleBelow, VAPOR, rX, cY);
        if (particleBelow == OIL)
          destroyParticlesBelow(ACID, particleBelow, FIRE, rX, cY);
         if (particleBelow == SAND)
          destroyParticlesBelow(ACID, particleBelow, SMOKE, rX, cY);
      }
      else if (!isBottomEmpty(rX, cY))
      {// if the bottom is not empty it will then check the sides to see if it can move left or right
        
        int topParticle = getParticleOnTop(rX, cY);
        int rightParticle = getParticleToRight(rX, cY);
        int leftParticle = getParticleToLeft(rX, cY);
        if (topParticle == SAND)
        {
          destroyParticlesOnTop(ACID, SAND, SMOKE, rX, cY);
        }
        if (rightParticle == SAND || leftParticle == SAND)
        {
          destroyParticlesOnSides(ACID, SAND, SMOKE, rX, cY);
        }
        else if (rightParticle == WATER || leftParticle == WATER)
        {
          destroyParticlesOnSides(ACID, WATER, VAPOR, rX, cY);
        }
        else if (rightParticle == OIL || leftParticle == OIL)
        {
          destroyParticlesOnSides(ACID, OIL, FIRE, rX, cY);
        }
        else
          liquidMovement(ACID, swappableParticles, rX, cY, bubbles);
      }
    }
    else if (isInLastRow(rX, cY))
    {//if it is in the last row it will wrap to the top and that module will check to see if the top is empty 
      //if it is then it will wrap if not then it will stay put
      wrapParticleToTop(ACID, swappableParticles, rX, cY);
    }
    
  }
  
  public void moveOilParticle(int rX, int cY)
  { 
    int[] swappableSpace = {EMPTY};    
    int[] swappableParticles = {EMPTY, WATER};
    boolean noBubbles = false;
    
    if (!isInLastRow(rX, cY))
    {//if it is not in the last row it will check to see if the bottom is swappable
      int particleBelow = getParticleOnBottom(rX, cY);
      if (isBottomSwappable(particleBelow, swappableSpace, rX, cY))
      {//if the bottom is empty it willmove down
        moveDown(OIL, particleBelow, rX, cY);
      }
      else if (!isBottomEmpty(rX, cY))
      {// if the bottom is not empty it will then check the sides to see if it can move left or right
        liquidMovement(OIL, swappableParticles, rX, cY, noBubbles);
      }
    }
    else if (isInLastRow(rX, cY))
    {//if it is in the last row it will wrap to the top and that module will check to see if the top is empty 
     //if it is then it will wrap if not then it will stay put
      wrapParticleToTop(OIL, swappableParticles, rX, cY);
    }
    
  }// end oil
  
  public void moveWaterParticle (int rX, int cY)
  {
    
    int[] swappableParticles = {EMPTY, OIL};
    boolean noBubbles = false;
    
    if (!isInLastRow(rX, cY))
    {//if it is not in the last row it will check to see if the bottom is empty
      int particleBelow = getParticleOnBottom(rX, cY);
      if (isBottomSwappable(particleBelow, swappableParticles, rX, cY))
      {//if the bottom is empty it willmove down
        moveDown(WATER, particleBelow, rX, cY);
      }
      else if (!isBottomEmpty(rX, cY))
      {// if the bottom is not empty it will then check the sides to see if it can move left or right
       
        liquidMovement(WATER, swappableParticles, rX, cY, noBubbles);
      }
    }
    else if (isInLastRow(rX, cY))
    {//if it is in the last row it will wrap to the top and that module will check to see if the top is empty 
     //if it is then it will wrap if not then it will stay put
      wrapParticleToTop(WATER, swappableParticles, rX, cY);
    }
    
  }//-------------end of water particle
  
  public void moveSandParticle(int rX, int cY){//-----------------------start SANDMOVE-----------
    int[] swappableParticles = {EMPTY, WATER, OIL};
    
    if (!isInLastRow(rX, cY))
    {//if it is not in the last row it will check to see if the bottom is empty
      int particleBelow = getParticleOnBottom(rX, cY);
      if (isBottomSwappable(particleBelow, swappableParticles, rX, cY))
      {//if the bottom is empty it willmove down
        moveDown(SAND, particleBelow, rX, cY);
      }
      else if (!isBottomSwappable(particleBelow, swappableParticles, rX, cY))
      {// if the bottom is not empty it will then check the sides to see if it can move left or right
        int dice = getRandomNumber(1, 6);
        
        if (dice > 4)
        {
          if (!isInLastColumn(rX, cY))
            moveDownRight(SAND, getParticleOnBottomRight(rX, cY), rX, cY);
          else
            wrapParticleRight(SAND, swappableParticles, rX, cY);
        }
        else if (dice <= 4)
        {
          if (!isInFirstColumn(rX, cY))
            moveDownLeft(SAND, getParticleOnBottomLeft(rX, cY), rX, cY);
          else
            wrapParticleLeft(SAND, swappableParticles, rX, cY);
        }
      }
    }
    else if (isInLastRow(rX, cY))
    {//if it is in the last row it will wrap to the top and that module will check to see if the top is empty 
     //if it is then it will wrap if not then it will stay put
      wrapParticleToTop(SAND, swappableParticles, rX, cY);
    }
    
  }//--------------------------------- end of SANDMOVE----------
  
  //================================================== MOVEMENT =========================================================
  //generic moving modules, pass in the particle to move and the particle it will swap with and its current row and column
  public void moveDown(int particleToMove, int particleBelow, int row, int col)
  {   
    sandGrid[row][col]   = particleBelow;
    sandGrid[row+1][col] = particleToMove;
    
  }
  
  public void moveDownRight(int particleToMove, int particleBelow, int row, int col)
  { 
    if (col+1 < MAX_COLS && isBottomRightEmpty(row, col) && isRightEmpty(row, col)){
      sandGrid[row][col] = particleBelow;
      sandGrid[row+1][col+1] = particleToMove;
    }
    else if (isInLastColumn(row, col))
    {
      //wrapParticleRight(particleToMove, row, col);
    }
      
  }
  
  public void moveDownLeft(int particleToMove, int particleBelow, int row, int col)
  {    
    if (col-1 >= 0 && isBottomLeftEmpty(row, col) && isLeftEmpty(row, col)){
      sandGrid[row][col] = particleBelow;
      sandGrid[row+1][col-1] = particleToMove;
    }
    else if (isInFirstColumn(row, col))
    {
      //wrapParticleLeft(particleToMove, row, col);
    }
  }
  
  
  void moveRight(int particleToMove, int particleToTheRight, int row, int col)
  {
    if (col+1 < MAX_COLS){
      sandGrid[row][col] = particleToTheRight;
      sandGrid[row][col+1] = particleToMove;
    }
    else if (isInLastColumn(row, col))
    {
      //wrapParticleRight(particleToMove, row, col);
    }
  }
  
  void moveLeft(int particleToMove, int particleToTheSide, int row, int col)
  {
    if (col-1 >= 0){
      sandGrid[row][col] = particleToTheSide;
      sandGrid[row][col-1] = particleToMove;
    }
    else if (isInFirstColumn(row, col))
    {
      //wrapParticleLeft(particleToMove, row, col);
    }
  }
  
  void moveUp(int particleToMove, int particleOnTop, int row, int col)
  {
    if (row-1 >= 0){
     sandGrid[row][col]   = particleOnTop;
    sandGrid[row-1][col] = particleToMove;
    }
    else if (isInTopRow(row, col))
    {
      //wrapParticleToBottom(particleToMove, row, col);
    }
  }
  
  void moveUpRight(int particleToMove, int particleTopRight, int row, int col)
  {
    if (col+1 < MAX_COLS){
      sandGrid[row][col] = particleTopRight;
      sandGrid[row][col+1] = particleToMove;
    }
    else if (isInLastColumn(row, col))
    {
      //wrapParticleRight(particleToMove, row, col);
    }
  }
  
  void moveUpLeft()
  {
    
  }
  
  void destroyParticle(int row, int col)
  {
    sandGrid[row][col] = EMPTY;
  }
  
  // ==================================================== end of generic moveModules ===================================
  
  public void wrapParticleToTop(int particle, int[] swapableParticles, int row, int col)
  {   
    for (int i =0; i < swapableParticles.length; i++)
    {
      if (sandGrid[0][col] == swapableParticles[i])
      {
        sandGrid[row][col] = swapableParticles[i];
        sandGrid[0][col]   = particle;
      }
    }
  }// end of wrap module
  
  void wrapParticleToBottom(int particle, int[] swapableParticles, int row, int col)
  {
    for (int i =0; i < swapableParticles.length; i++)
    {
      if (sandGrid[MAX_ROWS-1][col] == swapableParticles[i])
      {
        sandGrid[row][col] = swapableParticles[i];
        sandGrid[MAX_ROWS-1][col]   = particle;
      }
    }
  }
  
  void wrapParticleRight(int particle, int[] swapableParticles, int row, int col)
  {
    for (int i =0; i < swapableParticles.length; i++)
    {
      if(sandGrid[row][0] == swapableParticles[i])
      {
        sandGrid[row][col] = swapableParticles[i];
        sandGrid[row][0] = particle;
      }
    }
  }
  
  void wrapParticleLeft(int particle, int[] swapableParticles, int row, int col)
  {
    for (int i =0; i < swapableParticles.length; i++)
    {
      if(sandGrid[row][MAX_COLS-1] == swapableParticles[i])
      {
        sandGrid[row][col] = swapableParticles[i];
        sandGrid[row][MAX_COLS-1] = particle;
      }
    }
  }
  // ---------------------------------------------End wrapping -----------------------------------------------------
  
///////////////////////Begin checks
  public boolean isInLastRow(int row, int col)
  {
    
    if((row+1) >= MAX_ROWS)
    {//if the row below is the same as the max row then its not in bounds
      return true;
    }
    else 
    {//otherwise the particle is not at the bottom of the grid
      return false;
    }
  }
  
  boolean isInTopRow(int row, int col)
  {
    if(row-1 < 0)
    {
      return true;
    }
    else return false;
  }
  
  boolean isInLastColumn(int row, int col)
  {
    if (col+1 >= MAX_COLS)
    {
      return true;
    }
    else return false;
  }
  
  boolean isInFirstColumn(int row, int col)
  {
    if (col-1 < 0)
    {
      return true;
    }
    else return false;
  }
  
  //`````````````````````````checks for empty spaces to move to``````````````````````````````````````
  public boolean isBottomEmpty(int row, int col)
  {
    
    if (sandGrid[row+1][col] == EMPTY)
      return true;
    else return false;
    
  } 
  
  boolean isTopEmpty(int row, int col)
  {
    if (sandGrid[row-1][col] == EMPTY)
      return true;
    else return false;
  }
  
  public boolean isBottomRightEmpty(int row, int col)
  {    
    if (sandGrid[row+1][col+1] == EMPTY)
      return true;
    else return false;
    
  }
  
  public boolean isBottomLeftEmpty(int row, int col)
  {
    
    if (sandGrid[row+1][col-1] == EMPTY)
      return true;
    else return false;
    
  }
  
  boolean isRightEmpty(int row, int col)
  {
    if (sandGrid[row][col+1] == EMPTY)
      return true;
    else return false;
  }
  
  boolean isLeftEmpty(int row, int col)
  {
    if (sandGrid[row][col-1] == EMPTY)
      return true;
    else return false;
  }
  
  //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  SWAPPABLES  <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
  boolean isTopParticleSwappable(int particleToCheck, int[]swappableParticles, int row, int col)
  {
    if (row-1 < 0)
    {
      return false;
    }
    else //if (sandGrid[row-1][col] == swappableParticle)
    {
      for (int i = 0; i < swappableParticles.length; i++)
      {
        if (particleToCheck == swappableParticles[i])
          return true;
      }
    }
    return false;
  }
  
  boolean isBottomSwappable(int particleToCheck, int[] swappableParticles, int row, int col)
  {
    for (int i = 0; i < swappableParticles.length; i++)
    {
      if (particleToCheck == swappableParticles[i])
        return true;
    }
    return false;
  }
    
  boolean isRightSwappable(int particleToRight, int[] swappableParticles, int row, int col)
  {
    for (int i = 0; i < swappableParticles.length; i++)
    {
      if (particleToRight == swappableParticles[i])
        return true;
    }
    return false;
  }
  
  boolean isLeftSwappable(int particleToLeft, int[] swappableParticles, int row, int col)
  {
    for (int i = 0; i < swappableParticles.length; i++)
    {
      if (particleToLeft == swappableParticles[i])
        return true;
    }
    return false;
  }
  boolean isTopRightSwappable(int particleTopRight, int[] swappableParticles, int row, int col)
  {
    for (int i = 0; i < swappableParticles.length; i++)
    {
      if (particleTopRight == swappableParticles[i])
        return true;
    }
    return false;
  }
  
  boolean isTopLeftSwappable(int particleTopLeft, int[] swappableParticles, int row, int col)
  {
    for (int i = 0; i < swappableParticles.length; i++)
    {
      if (particleTopLeft == swappableParticles[i])
        return true;
    }
    return false;
  }
  
  
 // >>>>>>>>>>>>>>>>>>>>>>>>>>>> end of SWAPPABLES >>>>>>>>>>>>>>>>>>>>>>>>>>>  
   
/// <<<<<<<<<<<<<<<<<<< get particles on top bottom and immediate sides   >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>!!!!
   
   int getParticleOnBottom(int row, int col)
   {
     if (isInLastRow(row, col))
       return sandGrid[0][col];
     else
       return sandGrid[row+1][col];
   }
   
   int getParticleToRight(int row, int col)
   {
     if (isInLastColumn(row, col))
       return sandGrid[row][0];
     else
       return sandGrid[row][col+1];
   }
   
   int getParticleToLeft(int row, int col)
   {
     if (isInFirstColumn(row, col))
       return sandGrid[row][MAX_COLS-1];
     else
       return sandGrid[row][col-1];
   }
   
   int getParticleOnTop(int row, int col)
   {
     if (isInTopRow(row, col))
       return sandGrid[0][col];
     else
       return sandGrid[row-1][col];
   }

   // get particles in the corners
   int getParticleOnBottomRight(int row, int col)
   {
      if (isInLastColumn(row, col))
       return sandGrid[row+1][0];
     else
       return sandGrid[row+1][col+1];
   }
   
   int getParticleOnTopRight(int row, int col)
   {
      if (isInLastColumn(row, col))
       return sandGrid[row-1][0];
     else
       return sandGrid[row-1][col+1];
   }
   
   int getParticleOnTopLeft(int row, int col)
   {
     if (isInFirstColumn(row, col))
       return sandGrid[row-1][MAX_COLS-1];
     else
       return sandGrid[row-1][col-1];
   }
   
   int getParticleOnBottomLeft(int row, int col)
   {
     if (isInFirstColumn(row, col))
       return sandGrid[row+1][MAX_COLS-1];
     else
       return sandGrid[row+1][col-1];
   }
   
   
//DO NOT modify anything below here!!! /////////////////////////////////////////////////////////////////
  public void run(){
    while (true){
      for (int i = 0; i < display.getSpeed(); i++)
        step();
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse   
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
    }
  }
  
  public int getRandomNumber (int low, int high){
    return (int)(Math.random() * (high - low)) + low;
  }
}