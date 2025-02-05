from flask import Flask, request, jsonify
import json

app = Flask(__name__)

# נתוני דוגמה (ניתן להחליף במסד נתונים אמיתי)
artists_data = [
    {"name": "Adele", "type": "Singer"},
    {"name": "Beethoven", "type": "Composer"},
    {"name": "Beyoncé", "type": "Singer"},
    {"name": "Chopin", "type": "Composer"},
    {"name": "Drake", "type": "Rapper"},
    {"name": "Eminem", "type": "Rapper"},
    {"name": "Freddie Mercury", "type": "Singer"},
    {"name": "Gustav Mahler", "type": "Composer"},
]

@app.route('/artists', methods=['GET'])
def get_artists():
    """
    מחזיר רשימה של אומנים, מסודרים לפי סדר אלפביתי.
    תומך בסינון לפי אות התחלה (`letter`) וסוג אומן (`type`).
    """
    letter = request.args.get('letter', '').upper()
    artist_type = request.args.get('type', '')

    filtered_artists = sorted(artists_data, key=lambda x: x['name'])

    if letter:
        filtered_artists = [a for a in filtered_artists if a['name'].upper().startswith(letter)]
    
    if artist_type:
        filtered_artists = [a for a in filtered_artists if a['type'].lower() == artist_type.lower()]

    return jsonify([a['name'] for a in filtered_artists])

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0', port=5000)