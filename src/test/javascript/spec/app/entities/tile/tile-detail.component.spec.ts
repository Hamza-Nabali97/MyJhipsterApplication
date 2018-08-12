/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterappTestModule } from '../../../test.module';
import { TileDetailComponent } from 'app/entities/tile/tile-detail.component';
import { Tile } from 'app/shared/model/tile.model';

describe('Component Tests', () => {
    describe('Tile Management Detail Component', () => {
        let comp: TileDetailComponent;
        let fixture: ComponentFixture<TileDetailComponent>;
        const route = ({ data: of({ tile: new Tile(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterappTestModule],
                declarations: [TileDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TileDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TileDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tile).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
