public class Corredor extends Atleta implements Equipamento{
    @Override
    public void treinar()
    {
        System.out.println("Corredor está treinando...");
    }
    @Override
    public void colocarEquipamento()
    {
        System.out.println("Corredor está colocando equipamento...");
    }
}
