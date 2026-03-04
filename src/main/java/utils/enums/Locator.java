package utils.enums;

public sealed interface Locator permits HeaderMenuItem, FooterMenuItem {
    String getLocator();
}
