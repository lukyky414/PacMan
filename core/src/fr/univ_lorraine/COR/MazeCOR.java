package fr.univ_lorraine.COR;


public class MazeCOR {
	private static MazeCOR instance = new MazeCOR();

	private Expert cor;

	private MazeCOR(){
		cor = new expertBlock(
				new expertPellet(null)
		);
	}

	public static Expert getCOR(){
		return instance.cor;
	}
}
