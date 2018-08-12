import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { CompanyComponentsPage, CompanyUpdatePage } from './company.page-object';

describe('Company e2e test', () => {
    let navBarPage: NavBarPage;
    let companyUpdatePage: CompanyUpdatePage;
    let companyComponentsPage: CompanyComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Companies', () => {
        navBarPage.goToEntity('company');
        companyComponentsPage = new CompanyComponentsPage();
        expect(companyComponentsPage.getTitle()).toMatch(/jhipsterappApp.company.home.title/);
    });

    it('should load create Company page', () => {
        companyComponentsPage.clickOnCreateButton();
        companyUpdatePage = new CompanyUpdatePage();
        expect(companyUpdatePage.getPageTitle()).toMatch(/jhipsterappApp.company.home.createOrEditLabel/);
        companyUpdatePage.cancel();
    });

    it('should create and save Companies', () => {
        companyComponentsPage.clickOnCreateButton();
        companyUpdatePage.setNameInput('name');
        expect(companyUpdatePage.getNameInput()).toMatch('name');
        companyUpdatePage.setLocationInput('location');
        expect(companyUpdatePage.getLocationInput()).toMatch('location');
        companyUpdatePage.save();
        expect(companyUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
