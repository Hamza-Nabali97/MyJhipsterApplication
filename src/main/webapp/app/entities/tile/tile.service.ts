import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITile } from 'app/shared/model/tile.model';

type EntityResponseType = HttpResponse<ITile>;
type EntityArrayResponseType = HttpResponse<ITile[]>;

@Injectable({ providedIn: 'root' })
export class TileService {
    private resourceUrl = SERVER_API_URL + 'api/tiles';

    constructor(private http: HttpClient) {}

    create(tile: ITile): Observable<EntityResponseType> {
        return this.http.post<ITile>(this.resourceUrl, tile, { observe: 'response' });
    }

    update(tile: ITile): Observable<EntityResponseType> {
        return this.http.put<ITile>(this.resourceUrl, tile, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITile>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITile[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
