import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Tile } from 'app/shared/model/tile.model';
import { TileService } from './tile.service';
import { TileComponent } from './tile.component';
import { TileDetailComponent } from './tile-detail.component';
import { TileUpdateComponent } from './tile-update.component';
import { TileDeletePopupComponent } from './tile-delete-dialog.component';
import { ITile } from 'app/shared/model/tile.model';

@Injectable({ providedIn: 'root' })
export class TileResolve implements Resolve<ITile> {
    constructor(private service: TileService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((tile: HttpResponse<Tile>) => tile.body));
        }
        return of(new Tile());
    }
}

export const tileRoute: Routes = [
    {
        path: 'tile',
        component: TileComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'jhipsterappApp.tile.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tile/:id/view',
        component: TileDetailComponent,
        resolve: {
            tile: TileResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterappApp.tile.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tile/new',
        component: TileUpdateComponent,
        resolve: {
            tile: TileResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterappApp.tile.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tile/:id/edit',
        component: TileUpdateComponent,
        resolve: {
            tile: TileResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterappApp.tile.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tilePopupRoute: Routes = [
    {
        path: 'tile/:id/delete',
        component: TileDeletePopupComponent,
        resolve: {
            tile: TileResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'jhipsterappApp.tile.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
