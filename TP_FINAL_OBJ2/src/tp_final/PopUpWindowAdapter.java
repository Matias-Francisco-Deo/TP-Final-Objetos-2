package tp_final;

public class PopUpWindowAdapter implements Suscriptor {
	// OBJECT ADAPTER

	private PopUpWindow popUpWindow;

	public PopUpWindowAdapter(PopUpWindow popUpWindow) {
		this.setPopUpWindow(popUpWindow);
	}

	public PopUpWindow getPopUpWindow() {
		return popUpWindow;
	}

	private void setPopUpWindow(PopUpWindow popUpWindow) {
		this.popUpWindow = popUpWindow;
	}

	@Override
	public void mandarMensaje(String mensaje) {
		this.popUpWindow.popUp(mensaje, "#fffff", 12);

	}
}
