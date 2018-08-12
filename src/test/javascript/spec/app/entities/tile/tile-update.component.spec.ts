/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JhipsterappTestModule } from '../../../test.module';
import { TileUpdateComponent } from 'app/entities/tile/tile-update.component';
import { TileService } from 'app/entities/tile/tile.service';
import { Tile } from 'app/shared/model/tile.model';

describe('Component Tests', () => {
    describe('Tile Management Update Component', () => {
        let comp: TileUpdateComponent;
        let fixture: ComponentFixture<TileUpdateComponent>;
        let service: TileService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JhipsterappTestModule],
                declarations: [TileUpdateComponent]
            })
                .overrideTemplate(TileUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TileUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TileService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Tile(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tile = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Tile();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tile = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
