import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITile } from 'app/shared/model/tile.model';

@Component({
    selector: 'jhi-tile-detail',
    templateUrl: './tile-detail.component.html'
})
export class TileDetailComponent implements OnInit {
    tile: ITile;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tile }) => {
            this.tile = tile;
        });
    }

    previousState() {
        window.history.back();
    }
}
