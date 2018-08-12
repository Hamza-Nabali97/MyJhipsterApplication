import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { ITile } from 'app/shared/model/tile.model';
import { TileService } from './tile.service';
import { ICompany } from 'app/shared/model/company.model';
import { CompanyService } from 'app/entities/company';

@Component({
    selector: 'jhi-tile-update',
    templateUrl: './tile-update.component.html'
})
export class TileUpdateComponent implements OnInit {
    private _tile: ITile;
    isSaving: boolean;

    companies: ICompany[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private tileService: TileService,
        private companyService: CompanyService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ tile }) => {
            this.tile = tile;
        });
        this.companyService.query().subscribe(
            (res: HttpResponse<ICompany[]>) => {
                this.companies = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tile.id !== undefined) {
            this.subscribeToSaveResponse(this.tileService.update(this.tile));
        } else {
            this.subscribeToSaveResponse(this.tileService.create(this.tile));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITile>>) {
        result.subscribe((res: HttpResponse<ITile>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCompanyById(index: number, item: ICompany) {
        return item.id;
    }
    get tile() {
        return this._tile;
    }

    set tile(tile: ITile) {
        this._tile = tile;
    }
}
