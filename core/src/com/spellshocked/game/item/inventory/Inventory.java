package com.spellshocked.game.item.inventory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.spellshocked.game.item.Item;

import java.util.*;

public class Inventory implements List<Item> {

    //1d array of items
    protected Item[] inventory;
    protected Texture slot;
    protected JsonValue contents;

    //constructor to pass the length of the array
    public Inventory(int size, String path) {
        inventory = new Item[size];
        JsonReader reader = new JsonReader();
        contents = reader.parse(Gdx.files.internal(path));
        slot = new Texture(contents.getString("slot"));
    }

    /**
     * @return int, length of inventory
     */
    @Override
    public int size() {
        return inventory.length;
    }


    /**
     * Checks if inventory is empty
     *
     * @return true if empty
     */
    @Override
    public boolean isEmpty() {
        //foreach check for null
        for (Item i : inventory) {
            if (i != null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if inventory contains a specific Object
     *
     * @param o, Object to look for
     * @return true if found
     */
    @Override
    public boolean contains(Object o) {
        //foreach check ==
        for (Item i : inventory) {
            if (i == o) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Item> iterator() {
        return Arrays.stream(inventory).iterator();
    }

    @Override
    public Object[] toArray() {
        return inventory;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        //ignore
        return null;
    }

    /**
     * Adds Item to first available spot in inventory
     *
     * @param item, Item to add
     * @return true if added
     */
    @Override
    public boolean add(Item item) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == null) {
                inventory[i] = item;
                return true;
            }
        }
        return false;
    }

    /**
     * Adds an Item to a specific index if an Item isn't there
     *
     * @param index, index where the Item is placed
     * @param element, the Item
     */
    @Override
    public void add(int index, Item element) {
        for (int i = index; i < inventory.length; i++) {
            if (inventory[i] == null) {
                inventory[i] = element;
                break;
            }
        }
    }

    /**
     * Removes a specific Object in the inventory if found
     *
     * @param o, the Object to get removed
     * @return true if removed
     */
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] == o) {
                inventory[i] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if all the Objects in a Collection are in inventory
     *
     * @param c, the Collection
     * @return true if all Objects in c are found in inventory
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object i2 : c){
            boolean b2 = false;
            for (Item item : inventory) {
                if (item == i2) {
                    b2 = true;
                    break;
                }
            }
            if(!b2) return false;
        }
        return true;
    }

    /**
     * Adds all Objects in a Collection to inventory until there are no spots left
     *
     * @param c, the Collection to be added to inventory
     * @return true if all Objects in c are added
     */
    @Override
    public boolean addAll(Collection<? extends Item> c) {
        return addAll(0, c);
    }

    /**
     * Adds all Objects in a Collection to inventory at a specific index until there are no spots left
     *
     * @param index, the index in inventory where Objects in c are added
     * @param c, the Collection to be added to inventory
     * @return true if all Objects in c are added
     */
    @Override
    public boolean addAll(int index, Collection<? extends Item> c) {
        //fill empty spots starting at i
        int j = 0;
        for (int i = index; i < inventory.length; i++) {
            if (!(j < c.toArray().length)) {
                break;
            }
            if (inventory[i] == null) inventory[i] = (Item) c.toArray()[j++];
        }
        return j>=c.size();
    }

    /**
     * Removes all Items in inventory that are the same as the Objects in the Collection
     *
     * @param c, the Collection to be removed from inventory
     * @return true if all Objects in c are removed from the inventory
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean b = true;
        for(Object i2 : c){
            boolean b2 = false;
            for (int i = 0; i < inventory.length; i++) {
                if (inventory[i] == i2){
                    inventory[i] = null;
                    b2 = true;
                }
            }
            if(!b2) b = false;
        }
        return b;
    }

    /**
     * Goes through the inventory and removes all Items that aren't in Collection c
     *
     * @param c, the Collection of Objects that are retained in the inventory
     * @return true if all Objects in c are kept in inventory
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean b = true;
        for (int i = 0; i < inventory.length; i++) {
            boolean b2 = false;
            for (Object i2 : c) {
                if (inventory[i] == i2) {
                    b2 = true;
                    break;
                }
            }
            if(!b2) inventory[i] = null;
            else b = false;
        }
        return b;
    }

    /**
     * Clears every Item in inventory
     */
    @Override
    public void clear() {
        Arrays.fill(inventory, null);
    }

    /**
     * Gets item at index in inventory
     *
     * @param index, index of Item in inventory to get
     * @return Item, Item at index
     */
    @Override
    public Item get(int index) {
        return inventory[index];
    }

    /**
     * Sets item at index in inventory with Item element
     *
     * @param index, index of Item in inventory to set
     * @param element, Item replacing previous Item
     * @return Item, the Item that was replaced at index
     */
    @Override
    public Item set(int index, Item element) {
        Item before = inventory[index];
        inventory[index] = element;
        return before;
    }

    /**
     * Removes an Item at a certain index in inventory
     *
     * @param index, index of Item in inventory to remove
     * @return Item, the Item that was replaced at index
     */
    @Override
    public Item remove(int index) {
        return set(index, null);
    }

    /**
     * Finds the index of an Object in inventory
     *
     * @param o, the Object that is being found in inventory
     * @return int, the index of Object o in the inventory, returns -1 if not found
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the last index of a specific Object in inventory
     *
     * @param o, the Object that is being found in inventory
     * @return int, the last index of Object o in the inventory, returns -1 if not found
     */
    @Override
    public int lastIndexOf(Object o) {
        for (int i = inventory.length - 1; i > -1; i--) {
            if (inventory[i] != null && inventory[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        String r = "(";
        for (Item item : inventory) {
            if (item != null) r += item.getName() + ", ";
            else r += "NULL, ";
        }
        return r.substring(0, r.length()-2) + ")";
    }

    @Override
    public ListIterator<Item> listIterator() {
        //ignore
        return null;
    }

    @Override
    public ListIterator<Item> listIterator(int index) {
        //ignore
        return null;
    }

    @Override
    public List<Item> subList(int fromIndex, int toIndex) {
        return null;
    }

    public void draw(Batch b, float x, float y){
            for(int i = 0; i<inventory.length; i++){
                b.draw(slot, x+i*32, y, 32, 32);
                if(inventory[i] != null) b.draw(inventory[i], x+i*32+4, y+4, 24, 24);
            }
    }
}
