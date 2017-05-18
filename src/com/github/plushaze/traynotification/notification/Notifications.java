package com.github.plushaze.traynotification.notification;

public enum Notifications implements Notification {

	INFORMATION("Ressources/info.png", "#2C54AB"),
	NOTICE("Ressources/notice.png", "#8D9695"),
	SUCCESS("Ressources/success.png", "#009961"),
	WARNING("Ressources/warning.png", "#E23E0A"),
	ERROR("Ressources/error.png", "#CC0033");

	private final String urlResource;
	private final String paintHex;

	Notifications(String urlResource, String paintHex) {
		this.urlResource = urlResource;
		this.paintHex = paintHex;
	}

	@Override
	public String getURLResource() {
		return urlResource;
	}

	@Override
	public String getPaintHex() {
		return paintHex;
	}

}
