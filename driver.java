import java.util.Random;
public class driver {
	public static void main(String args[]) {
		QKD(12, Basis.HV, Basis.DA);

		QCT(12, Basis.HV, Basis.DA);
	
	}
	
	
	
	//make the photons to be sent
	public static Photon[] genPhotons(int numOfPhotons, Basis b1, Basis b2) {
		Photon[] photons = new Photon[numOfPhotons]; 
		for(int i = 0; i < numOfPhotons; i++) {
			photons[i] = new Photon(b1, b2);
		}
		return photons;
	}
	
	public static int QCT(int num, Basis b1, Basis b2) {
		System.out.println("Flipping 'Coin'...");
		Random rd = new Random();
		Photon[] photons = genPhotons(num, b1, b2);
		
		Basis[] AliceBasis = new Basis[num];
		boolean[] AlicePol = new boolean[num];
		
		for(int i = 0; i < num; i++) {
			AliceBasis[i] = photons[i].getBasis();
		}
		for(int i = 0; i < num; i++) {
			AlicePol[i] = photons[i].getPol();
		}
		
		Basis[] BobBasis = new Basis[num];
		boolean[] BobPol = new boolean[num];
		boolean[] BobDetect = new boolean[num];
		
		for(int i = 0; i < num; i++) {
			if(rd.nextBoolean()) {
				BobBasis[i] = b1;
			} else {
				BobBasis[i] = b2;
			}
		}
		
		for(int i = 0; i < num; i++) {
			BobPol[i] = rd.nextBoolean();
		}
		
		int firstDetect = -1;
		for(int i = 0; i < num; i++) {
			BobDetect[i] = photons[i].filter(BobBasis[i], BobPol[i]);
			if(firstDetect == -1 && BobDetect[i]) {
				firstDetect = i;
			}
		}
		boolean b = rd.nextBoolean();
		if(AliceBasis[firstDetect] == BobBasis[firstDetect]) {
			if(AlicePol[firstDetect] == BobPol[firstDetect]) {
				if(!BobDetect[firstDetect]) {
					System.out.println("Cheating detected!");
					return -1;
				}
			}
		} 
		
		boolean flip = ((b&&!AlicePol[firstDetect])||(!b&&AlicePol[firstDetect]));
			System.out.println("Coin reads: " + flip + "!");
			if(flip) {
				return 1;
			} 
			return 0;
		
		
	}
	public static void QKD(int num, Basis b1, Basis b2) {
		System.out.println("Generating Key...");
		Random rd = new Random();
		Photon[] photons = genPhotons(num, b1, b2);
		
		Basis[] AliceBasis = new Basis[num];
		boolean[] AlicePol = new boolean[num];
		
		for(int i = 0; i < num; i++) {
			AliceBasis[i] = photons[i].getBasis();
		}
		for(int i = 0; i < num; i++) {
			AlicePol[i] = photons[i].getPol();
		}
		
		Basis[] BobBasis = new Basis[num];
		boolean[] BobPol = new boolean[num];
		boolean[] BobDetect = new boolean[num];
		
		for(int i = 0; i < num; i++) {
			if(rd.nextBoolean()) {
				BobBasis[i] = b1;
			} else {
				BobBasis[i] = b2;
			}
		}
		
		for(int i = 0; i < num; i++) {
			BobPol[i] = rd.nextBoolean();
		}
		
		for(int i = 0; i < num; i++) {
			BobDetect[i] = photons[i].filter(BobBasis[i], BobPol[i]);
		}
		
		for (int i = 0; i< num; i++){
			System.out.print(AliceBasis[i] + "     ");
		}
		System.out.println();
		for (int i = 0; i< num; i++){
			System.out.print(AlicePol[i] + "  ");
		}
		System.out.println();
		for (int i = 0; i< num; i++){
			System.out.print(BobBasis[i] + "     ");
		}
		System.out.println();
		for (int i = 0; i< num; i++){
			System.out.print(BobPol[i] + "  ");
		}
		System.out.println();
		for (int i = 0; i< num; i++){
			System.out.print(BobDetect[i] + "  ");
		}
		System.out.println();
		int[] finalKey = new int[num];
		for (int i = 0; i< num; i++){
			if(BobBasis[i] != AliceBasis[i]) {
				finalKey[i] = -2;
			} else if((AlicePol[i] == BobPol[i] && BobDetect[i]) || 
					(AlicePol[i] != BobPol[i] && !BobDetect[i])) {
				if(AlicePol[i]) {
					finalKey[i] = 1;
				} else {
					finalKey[i] = 0;
				}
			} else {
				finalKey[i]= -1;
			}
		}
		
		System.out.println("Final key is below, sans negative numbers:");
		for (int i = 0; i< num; i++){
			System.out.print(finalKey[i] + "  ");
		}
		System.out.println();
		
		
		
	}
	
}
