package java.itk.ppke.hu;

// Csak olyan madarakat engedünk, melyek tudnak repülni
public abstract sealed class Bird permits Eagle, Owl{
    public abstract void fly();
}
