import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { TileComponentsPage, TileUpdatePage } from './tile.page-object';

describe('Tile e2e test', () => {
    let navBarPage: NavBarPage;
    let tileUpdatePage: TileUpdatePage;
    let tileComponentsPage: TileComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Tiles', () => {
        navBarPage.goToEntity('tile');
        tileComponentsPage = new TileComponentsPage();
        expect(tileComponentsPage.getTitle()).toMatch(/jhipsterappApp.tile.home.title/);
    });

    it('should load create Tile page', () => {
        tileComponentsPage.clickOnCreateButton();
        tileUpdatePage = new TileUpdatePage();
        expect(tileUpdatePage.getPageTitle()).toMatch(/jhipsterappApp.tile.home.createOrEditLabel/);
        tileUpdatePage.cancel();
    });

    it('should create and save Tiles', () => {
        tileComponentsPage.clickOnCreateButton();
        tileUpdatePage.setNameInput('name');
        expect(tileUpdatePage.getNameInput()).toMatch('name');
        tileUpdatePage.setCodeInput('code');
        expect(tileUpdatePage.getCodeInput()).toMatch('code');
        tileUpdatePage.setSizeInput('size');
        expect(tileUpdatePage.getSizeInput()).toMatch('size');
        tileUpdatePage.setPlacementInput('placement');
        expect(tileUpdatePage.getPlacementInput()).toMatch('placement');
        tileUpdatePage.setPolishTypeInput('polishType');
        expect(tileUpdatePage.getPolishTypeInput()).toMatch('polishType');
        tileUpdatePage.setCostPriceInput('5');
        expect(tileUpdatePage.getCostPriceInput()).toMatch('5');
        tileUpdatePage.setMinimumSellingPriceInput('5');
        expect(tileUpdatePage.getMinimumSellingPriceInput()).toMatch('5');
        tileUpdatePage.setInventoryQunatityInput('5');
        expect(tileUpdatePage.getInventoryQunatityInput()).toMatch('5');
        tileUpdatePage.companySelectLastOption();
        tileUpdatePage.save();
        expect(tileUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
