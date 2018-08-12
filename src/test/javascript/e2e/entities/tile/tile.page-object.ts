import { element, by, promise, ElementFinder } from 'protractor';

export class TileComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-tile div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class TileUpdatePage {
    pageTitle = element(by.id('jhi-tile-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    codeInput = element(by.id('field_code'));
    sizeInput = element(by.id('field_size'));
    placementInput = element(by.id('field_placement'));
    polishTypeInput = element(by.id('field_polishType'));
    costPriceInput = element(by.id('field_costPrice'));
    minimumSellingPriceInput = element(by.id('field_minimumSellingPrice'));
    inventoryQunatityInput = element(by.id('field_inventoryQunatity'));
    companySelect = element(by.id('field_company'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setNameInput(name): promise.Promise<void> {
        return this.nameInput.sendKeys(name);
    }

    getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    setCodeInput(code): promise.Promise<void> {
        return this.codeInput.sendKeys(code);
    }

    getCodeInput() {
        return this.codeInput.getAttribute('value');
    }

    setSizeInput(size): promise.Promise<void> {
        return this.sizeInput.sendKeys(size);
    }

    getSizeInput() {
        return this.sizeInput.getAttribute('value');
    }

    setPlacementInput(placement): promise.Promise<void> {
        return this.placementInput.sendKeys(placement);
    }

    getPlacementInput() {
        return this.placementInput.getAttribute('value');
    }

    setPolishTypeInput(polishType): promise.Promise<void> {
        return this.polishTypeInput.sendKeys(polishType);
    }

    getPolishTypeInput() {
        return this.polishTypeInput.getAttribute('value');
    }

    setCostPriceInput(costPrice): promise.Promise<void> {
        return this.costPriceInput.sendKeys(costPrice);
    }

    getCostPriceInput() {
        return this.costPriceInput.getAttribute('value');
    }

    setMinimumSellingPriceInput(minimumSellingPrice): promise.Promise<void> {
        return this.minimumSellingPriceInput.sendKeys(minimumSellingPrice);
    }

    getMinimumSellingPriceInput() {
        return this.minimumSellingPriceInput.getAttribute('value');
    }

    setInventoryQunatityInput(inventoryQunatity): promise.Promise<void> {
        return this.inventoryQunatityInput.sendKeys(inventoryQunatity);
    }

    getInventoryQunatityInput() {
        return this.inventoryQunatityInput.getAttribute('value');
    }

    companySelectLastOption(): promise.Promise<void> {
        return this.companySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    companySelectOption(option): promise.Promise<void> {
        return this.companySelect.sendKeys(option);
    }

    getCompanySelect(): ElementFinder {
        return this.companySelect;
    }

    getCompanySelectedOption() {
        return this.companySelect.element(by.css('option:checked')).getText();
    }

    save(): promise.Promise<void> {
        return this.saveButton.click();
    }

    cancel(): promise.Promise<void> {
        return this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}
