import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Main {
	private static ArrayList<Profile> profiles = new ArrayList<Profile>();
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		//При первом запуске программы нам необходимо закоментировать
		//данную метод т.к. мы пытаемся десеарилизовать несуществующий файл
		profiles = (ArrayList<Profile>) deserData("profiles");
		Profile profile = new Profile();
		profile.setName(JOptionPane.showInputDialog(null, "Введите ваше имя"));
		profile.setSurname(JOptionPane.showInputDialog(null, "Введите ваше имя"));
		profiles.add(profile);
		
		for(Profile p: profiles){
			System.out.println(p.getName()+" "+p.getSurname());
		}
		serData("profiles", profiles);
	}
private static void serData(String file_name, Object obj) {
		
		// Исходящий поток программы
		try{
		FileOutputStream fileout = new FileOutputStream(file_name+".ser");
		ObjectOutputStream out = new ObjectOutputStream(fileout);
		out.writeObject(obj);
		fileout.close();
		out.close();
		}catch (FileNotFoundException e){
			System.out.println("Файл не найден");
		// Просто выходим при нахождении ошибки
			System.exit(1);
		} catch (IOException e){
			System.out.println("ошибка ввода-вывода");
			System.exit(2);
		}
		
	}

	private static Object deserData(String file_name) {
		Object retObject = null;
		try{
			FileInputStream fileIn = new FileInputStream(file_name+".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			retObject = in.readObject();
			fileIn.close();
			in.close();
			}catch (FileNotFoundException e){
				System.out.println("Файл не найден");
			// Просто выходим при нахождении ошибки
				System.exit(1);
			} catch (IOException e){
				System.out.println("ошибка ввода-вывода");
				System.exit(2);
			} catch (ClassNotFoundException e) {
				
				System.out.println("Класс не найден");
				System.exit(3);
			}
		return retObject;
		
	}
	

}
