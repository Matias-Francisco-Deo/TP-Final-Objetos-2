package tp_final.Suscriptores;

public class HomePagePublisherAdapter implements Suscriptor {

	private HomePagePublisher homePagePublisher;

	public HomePagePublisherAdapter(HomePagePublisher homePagePublisher) {
		this.setHomePagePublisher(homePagePublisher);
	}

	@Override
	public void mandarMensaje(String mensaje) {
		this.homePagePublisher.publish(mensaje);

	}

	public HomePagePublisher getHomePagePublisher() {
		return homePagePublisher;
	}

	private void setHomePagePublisher(HomePagePublisher homePagePublisher) {
		this.homePagePublisher = homePagePublisher;
	}

}
