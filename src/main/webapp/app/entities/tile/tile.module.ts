import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterappSharedModule } from 'app/shared';
import {
    TileComponent,
    TileDetailComponent,
    TileUpdateComponent,
    TileDeletePopupComponent,
    TileDeleteDialogComponent,
    tileRoute,
    tilePopupRoute
} from './';

const ENTITY_STATES = [...tileRoute, ...tilePopupRoute];

@NgModule({
    imports: [JhipsterappSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [TileComponent, TileDetailComponent, TileUpdateComponent, TileDeleteDialogComponent, TileDeletePopupComponent],
    entryComponents: [TileComponent, TileUpdateComponent, TileDeleteDialogComponent, TileDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterappTileModule {}
