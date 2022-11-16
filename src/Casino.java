/*
 * Arin Bindra
 * 
 */

public class Casino extends User{
    
    public Casino()
    {
        super("Casino");
        this.existingUser();
    }
    
    //----------------------------------------------------------------
    
    private void existingUser()
    {
        super.add(this.data.readCasinoData());
    }
    
    //----------------------------------------------------------------
    
    public void saveCasino()
    {
        this.data.writeCasinoData(this.getDollars());
    }
}
