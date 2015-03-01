package kpa.solver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kpa.model.KPuzzle;
import kpa.model.KCell;
import kpa.model.Direction;
import kpa.model.KEntry;
import kpa.command.Command;
import kpa.command.UndoRedo;
import kpa.command.SetCommand;
import kpa.gui.MainFrame;

/**
 * Kakuro Puzzle Solver Class.
 * 
 * @author Serousov Vitaly, last changes 11.12.2014
 */
public class Solver {
     
    /** Maximum number that can by typed in cell. */
    private static final int MAX = 9;
    
    /** The generator. */
    private KakuroCombinationGenerator generator;
    
    /** The generator observers. */
    private CompositeGeneratorObserver<Set<Integer>> observers;

    /** Collection of generated combinations and their permutations. */
    private List<List<Integer>> result = new ArrayList<List<Integer>>();
    
    /** Puzzle. */
    private KPuzzle puzzle;
    
    /** UndoRedo Stack. */
    private UndoRedo undoRedo;
    
    /**
     * Constructs solver.
     * 
     * @param puzzle Puzzle
     */
    public Solver(KPuzzle puzzle, UndoRedo undoRedo) {
        this.puzzle = puzzle;
        this.undoRedo = undoRedo;
    }
    
    /**
     * Get horizontal combinations using permutation
     * and excluding illegal combinations.
     * 
     * @param puzzle  Puzzle
     * @return  All combinations for all entries
     */
    public List<List<List<Integer>>> generateCombinations() {
        // Kakuro Combination Generator
        generator = new KakuroCombinationGenerator();
        // Set maximum number in cell
        generator.setMaxNumber(MAX);
        // Generator observer
        observers = new CompositeGeneratorObserver<Set<Integer>>();
        // Set observer
        generator.setObserver(observers);
        // PushPullAdapter connect to Lister
        PushPullAdapter adapter = new PushPullAdapter(new Lister(), true);
        // PushPull adapter to observer
        observers.add(adapter);
        
        // Collection that stores all combinations for all entries
        List<List<List<Integer>>> data = new ArrayList<List<List<Integer>>>();

        // Loop through all entries
        for (KEntry entry : puzzle.getEntries()) {
            
            // First row of entry
            int row = entry.getLocation().getRow();
            
            // First column of entry
            int column = entry.getLocation().getColumn();
            
            // Sum of elements of entry
            int sum = entry.getSpecification().getSum();
            
            // Number of elements of entry
            int length = entry.getSpecification().getLength();

            // Generator combinations
            generator.generate(sum, length);

            // Temporary combination store
            List<List<Integer>> combination = new ArrayList<List<Integer>>();

            // Loop through all combinations
            for (List<Integer> numbers : result) {
              
                // Work only with horizontal entries
                if (entry.getDirection() == Direction.HORIZONTAL) {
                    
                    // Go through columns
                    for (int i = column, j = 0; i < length + column; i++, j++) {
                        // Get cell by row and column
                        KCell temp = puzzle.getCell(row, i);
                        // Set state of cell from combination
                        temp.setState(numbers.get(j));
                    }
                    
                    // Add combination if it is legal
                    if (isValidEntry(entry)) {
                        // add to collection
                        combination.add(numbers);
                    }
                }                
            }

            // check for direction
            if (entry.getDirection() == Direction.HORIZONTAL) {
                // add combination
                data.add(combination);
            }
            
            // clear puzzle
            puzzle.clear();
            // clear combinations created by generator
            result.clear();
        }
        
        return data;
    }   
    
    /**
     * Backtrack recursive solver method.
     * 
     * @param combinations  Combinations for each entry
     * @param entryLevel  Recursive depth equals to entry number
     */
    public void solve(List<List<List<Integer>>> combinations, int entryLevel) {
        // Exit of recursion.
        if (puzzle.isSolved() || entryLevel == combinations.size()) {
            return;
        }

        // Get current entry by key
        KEntry kentry = puzzle.getEntries().get(entryLevel);

        // Loop through all combinations for current entry
        for (int i = 0; i < combinations.get(entryLevel).size(); i++) {
            
            // Fill entry from list of numbers
            fillEntry(kentry, combinations.get(entryLevel).get(i));

            // If entry is valid and it level is not higher than extent
            if (entryLevel < combinations.size() && isValidEntry(kentry)) {
                // call recursive with a deeper entry level
                solve(combinations, entryLevel + 1);
            }
            
            // Exit if puzzle is solved
            if (puzzle.isSolved()) {
                return;
            }
            
            // Clean cells
            cleanEntryCells(kentry);
        }
    }
    
    /**
     * Clean entry cells.
     * 
     * @param entry  Entry 
     */
    private void cleanEntryCells(KEntry entry) {
        // Loop through all cells in entry
        for (KCell cell : entry) {
            // Change state of cell
            changeState(cell, KCell.EMPTY);
        }
    }
    
    /**
     * Fill cells of entry with combination of numbers.
     * 
     * @param entry  Entry
     * @param combination  Combination
     */
    private void fillEntry(KEntry entry, List<Integer> combination) {
        int i = 0;
        // Loop through all cells in entry
        for (KCell cell : entry) {
            // Change state of cell
            changeState(cell, combination.get(i));
            ++ i;
        }
    }
    
    /**
     * Changes the cell state and save state in undoRedo stack.
     * 
     * @param cell  Cell
     * @param state  State
     */
    private void changeState(KCell cell, int state) {
        // Add cell and future state to undoRedo stack
        undoRedo.did(new SetCommand(cell, state));
        // Set state to cell
        cell.setState(state);
    }
    
    /**
     * Check whether illegal states of cells exists in entry.
     * 
     * @param kentry  Entry
     * @return  True, if there are no illegal states of cells.
     */
    private boolean isValidEntry(KEntry kentry) {
        // result of validation
        boolean result = true;
        
        // loop through all cells in entry
        for (KCell temp : kentry) {
            // if cell is illegal
            if (! temp.isOK()) {
                result = false;
                break;
            }
        }
        
        return result;
    }

    /**
     * Method for generation permutations.
     * 
     * @param original  List of numbers that need to be permuted.
     * @return   List of permutations of elements of list.
     */
    private List<List<Integer>> generatePermutations(List<Integer> original) {
        
        // If empty list.
        if (original.size() == 0) {
            List<List<Integer>> result = new ArrayList<List<Integer>>();
            result.add(new ArrayList<Integer>());
            return result;
        }
        
        // Remove first element.
        Integer first = original.remove(0);
        // Initialize result ArrayList
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // Recursive call
        List<List<Integer>> permutations = generatePermutations(original);
        
        // Move first element to different positions
        for (List<Integer> smaller : permutations) {
            for (int index = 0; index <= smaller.size(); index++) {
                List<Integer> temp = new ArrayList<Integer>(smaller);
                temp.add(index, first);
                result.add(temp);
            }
        }
        
        return result;
    }
    
    /**
     * Listener that add to collection generated combinations
     * and their permutations.
     */
    private class Lister implements GeneratorListener {

        @Override
        public void combinationGenerated(Set<Integer> combination) {
            // Convert set data to list
            List<Integer> comb = new ArrayList<Integer>();
            comb.addAll(combination);
            
            // Generate permutations for this combination
            List<List<Integer>> lists = generatePermutations(comb);
            
            // Add all combination to result array
            for (List<Integer> l : lists) {
                result.add(l);
            }
        }
    }
    
}
