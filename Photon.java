import java.util.Random;
public class Photon {
	//H, D, Clockwise = true
	//V, A, CC = false
	private boolean pol;
	private Basis basis;
	//generate a known photon
	public Photon(Basis b, boolean polIn) {
		pol = polIn;
		basis = b;
	}
	
	//generate a random photon
	public Photon(Basis b1, Basis b2) {
		Random rd = new Random();
		if(rd.nextBoolean()) {
			basis = b1;
		} else {
			basis = b2;
		}
		
		pol = rd.nextBoolean();
	}
	
	//try filtering the photon with a basis
	public boolean filter(Basis b, boolean orientation) {
		if(b == basis) {
			return (orientation == pol);
		}
		Random rd = new Random();
		basis = b;
		pol = rd.nextBoolean();
		return pol;
	}
	
	public boolean getPol(){
		return pol;
	}
	public Basis getBasis(){
		return basis;
	}
	public String toString() {
		return ("Basis: " + basis + " Polarized as :" + pol); 
	}
}
